package web.assets.service;

import org.springframework.data.jpa.domain.Specification;
import web.assets.model.Banner;

import web.assets.service.SpecSearchCriteria;
import web.assets.service.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BannerSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public BannerSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public BannerSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Banner> build() {
        if (params.size() == 0)
            return null;

        Specification<Banner> result = new BannerSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new BannerSpecification(params.get(i)))
                    : Specification.where(result).and(new BannerSpecification(params.get(i)));
        }

        return result;
    }

    public final BannerSpecificationsBuilder with(BannerSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final BannerSpecificationsBuilder with(SpecSearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}