package abox.assets.adt.service;

import abox.assets.adt.model.BannerDrm;
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
import java.util.ArrayList;
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
    public List<Banner> findByType(String type, Optional<Boolean> status, Optional<String> drm) {
        if(status.isEmpty()) {
            return bannerRepository.findByType(type)
                .orElseThrow(() -> new BannerNotFoundException(type));
        } else {
            if(drm.isEmpty()) {
                return bannerRepository.findByTypeAndStatus(type, status.get())
                        .orElseThrow(() -> new BannerNotFoundException(status.get(), null));
            } else {
                return bannerRepository.findByTypeAndStatusAndDrm(type, status.get(), drm.get())
                        .orElseThrow(() -> new BannerNotFoundException(status.get(), drm.get()));
            }
        }
    }

    @Override
    public List<Banner> findByTypeNotLike(String type, Optional<Boolean> status, Optional<String> drm) {
        if(status.isEmpty()) {
        return bannerRepository.findByTypeNotLike(type)
                .orElseThrow(() -> new BannerNotFoundException(type));
        } else {
            if(drm.isEmpty()) {
                return bannerRepository.findByTypeNotLikeAndStatus(type, status.get())
                        .orElseThrow(() -> new BannerNotFoundException(status.get(), null));
            } else {
                return bannerRepository.findByTypeNotLikeAndStatusAndDrm(type, status.get(), drm.get())
                        .orElseThrow(() -> new BannerNotFoundException(status.get(), drm.get()));
            }
        }
    }

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

    @Override
    public List<Object[]> convertBannerData(List<Banner> banners) {
        List<Object[]> complex = new ArrayList<>();
        Object[] bnInfo = new Object[0];
        for (Banner banner : banners) {
            String bnType = banner.type.getName();
            String bnDrm = null;
            if (banner.drm != null) {
                bnDrm = banner.drm.getName();
            }
            bnInfo = new Object[]{banner.getId(), banner.getName(), banner.getPath(), bnType, banner.getStatus(), bnDrm};
            complex.add(bnInfo);
        }
        return complex;
    }

    @Override
    public List<Banner> changeBannerData(List<Banner> banners) {
        int i = 0;
        for(Banner banner : banners){
            String path = banner.getPath();
            String img = path.replace(bannersLocationPath, "").replace("//", "/testbanners/");
            if (banner.drm == null) {
                banner.drm = new BannerDrm();
                banner.drm.setName("NONE");
            }
            banner.setImg(img);
            banners.set(i, banner);
            i++;
        }
        return banners;
    }
}
