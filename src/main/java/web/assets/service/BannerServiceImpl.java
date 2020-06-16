package web.assets.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import web.assets.dao.BannerDao;
import web.assets.model.Banner;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    private final BannerDao bannerDao;

    @Autowired
    public BannerServiceImpl(BannerDao bannerDao) {
        this.bannerDao = bannerDao;
    }

    @Override
    public Banner getBanner(int bannerID) {
        return bannerDao.getBannerById(bannerID)
                .orElseThrow(() -> new BannerNotFoundException(bannerID));
    }

    @Override
    public List<Banner> getBannerByType(String type) {
        return bannerDao.getBannerByType(type)
                .orElseThrow(() -> new BannerNotFoundException(type));
    }

    @Override
    public List<Banner> getBannerNotByType(String type) {
        return bannerDao.getBannerNotByType(type)
                .orElseThrow(() -> new BannerNotFoundException(type));
    }

    @Override
    public Banner getBannerMain(boolean status, String drm) {
        return bannerDao.getBannerMain(status, drm)
                .orElseThrow(() -> new BannerNotFoundException(status, drm));
    }

    @Override
    public List<Banner> getBannerComplex(boolean status, String drm) {
        return bannerDao.getBannerComplex(status, drm)
                .orElseThrow(() -> new BannerNotFoundException(status, drm));
    }

    @Override
    public List<Banner> getBannerAll() {
        return bannerDao.getBannerAll()
                .orElseThrow(() -> new BannerNotFoundException());
    }

    @Override
    public void updateBannerFile(String path, int id) {
        Banner banner = bannerDao.getBannerById(id)
                .orElseThrow(() -> new BannerNotFoundException(id));
        bannerDao.updateBannerFile(path, banner.getId());
    }

//    @Override
//    public boolean update(Banner banner, int id) {
//        if (bannerRepository.existsById(id)) {
//            banner.setId(id);
//            bannerRepository.save(banner);
//            return true;
//        }
//        return false;
//    }

}
