package web.assets.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import web.assets.dao.BannerDao;
import web.assets.model.Banner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    private final BannerDao bannerDao;

    @Value("${bannersLocationPath}")
    private String bannersLocationPath;

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

    @Override
    public void storeBanner(int id, MultipartFile file){
        if (!file.isEmpty()) {
            System.out.println("bannersLocationPath: " + Paths.get(bannersLocationPath));
            try {
                String pathToBanner = File.separator + Paths.get(bannersLocationPath) + File.separator + file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(pathToBanner)));
                stream.write(bytes);
                stream.close();
                Banner banner = bannerDao.getBannerById(id)
                        .orElseThrow(() -> new BannerNotFoundException(id));
                bannerDao.updateBannerFile(pathToBanner, banner.getId());
                //return "Вы удачно загрузили " + file.getOriginalFilename() + " в " + "/home/novikolay/test/download/" + file.getOriginalFilename();
            } catch (Exception e) {
                //return "Вам не удалось загрузить " + file.getOriginalFilename() + " => " + e.getMessage();
                throw new RuntimeException("FAIL! -> message = " + e.getMessage());
            }
        } else {
            //return "Вам не удалось загрузить " + file.getOriginalFilename() + " потому что файл пустой.";
            throw new RuntimeException("FAIL! -> message = " + file.getOriginalFilename() + "");
        }
    }

}
