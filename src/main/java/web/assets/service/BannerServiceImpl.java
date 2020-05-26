package web.assets.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import web.assets.model.Banner;
import web.assets.repository.BannerRepository;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;

    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public void create(Banner banner) {
        bannerRepository.save(banner);
    }

    @Override
    public List<Banner>  readAll() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner read(int id) {
        return bannerRepository.getOne(id);
    }

    @Override
    public boolean update(Banner banner, int id) {
        if (bannerRepository.existsById(id)) {
            banner.setId(id);
            bannerRepository.save(banner);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (bannerRepository.existsById(id)) {
            bannerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
