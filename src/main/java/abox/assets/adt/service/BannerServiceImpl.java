package abox.assets.adt.service;

import abox.assets.adt.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import abox.assets.adt.model.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository ;

    @Value("${bannersLocationPath}")
    private String bannersLocationPath;

    @Autowired
    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public Banner getOne(int bannerID) {
        return bannerRepository.getOne(bannerID);
    }

    @Override
    public List<Banner> findByType(String type, Optional<Boolean> status, Optional<String> drm) {
        if(status.isEmpty()) {
            return bannerRepository.findByType(type)
                .orElseThrow(() -> new BannerNotFoundException(type));
        } else {
            return bannerRepository.findByTypeAndStatusAndDrm(type, status.get(), drm.get())
                    .orElseThrow(() -> new BannerNotFoundException(status.get(), drm.get()));
        }
    }

    @Override
    public List<Banner> findByTypeNotLike(String type, Optional<Boolean> status, Optional<String> drm) {
        if(status.isEmpty()) {
        return bannerRepository.findByTypeNotLike(type)
                .orElseThrow(() -> new BannerNotFoundException(type));
        } else {
            return bannerRepository.findByTypeNotLikeAndStatusAndDrm(type, status.get(), drm.get())
                    .orElseThrow(() -> new BannerNotFoundException(status.get(), drm.get()));
        }
    }

//    @Override
//    public List<Banner> findByTypeOnly(String type) {
//        return bannerRepository.findByType(type)
//                .orElseThrow(() -> new BannerNotFoundException(type));
//    }
//
//    @Override
//    public List<Banner> findByTypeNotLikeOnly(String type) {
//        return bannerRepository.findByTypeNotLike(type)
//                .orElseThrow(() -> new BannerNotFoundException(type));
//    }

    @Override
    public boolean update(int id, MultipartFile path) {
        if (bannerRepository.existsById(id)) {
            try {
                String pathToBanner = File.separator + Paths.get(bannersLocationPath) + File.separator + path.getOriginalFilename();
                byte[] bytes = path.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(pathToBanner)));
                stream.write(bytes);
                stream.close();
                Banner banner = bannerRepository.getOne(id);
                banner.setPath(pathToBanner);
                bannerRepository.save(banner);
                return true;
            } catch (Exception e) {
                throw new RuntimeException("FAIL! -> message = " + e.getMessage());
            }
        }
        return false;
    }
}
