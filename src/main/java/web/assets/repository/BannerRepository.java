package web.assets.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.assets.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//public interface BannerRepository extends JpaRepository<Banner, Integer> {
////    static List<Banner> findAllByType(String type) {
////    }
////    @Query("FROM Banner WHERE type = :main")
////    static List<Banner> findAllByType(@Param("type") String type);
//}

public interface BannerRepository extends JpaRepository<Banner, Integer>, JpaSpecificationExecutor<Banner> {}