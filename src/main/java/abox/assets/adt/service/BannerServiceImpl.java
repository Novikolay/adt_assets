package abox.assets.adt.service;

import abox.assets.adt.model.BannerData;
import abox.assets.adt.model.BannerDrm;
import abox.assets.adt.model.BannerType;
import abox.assets.adt.repository.BannerDrmRepository;
import abox.assets.adt.repository.BannerRepository;
import abox.assets.adt.repository.BannerTypeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import abox.assets.adt.model.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final BannerTypeRepository bannerTypeRepository;
    private final BannerDrmRepository bannerDrmRepository;

    @Value("${bannersLocationPath}")
    private String bannersLocationPath;

    @Autowired
    public BannerServiceImpl(BannerRepository bannerRepository, BannerTypeRepository bannerTypeRepository, BannerDrmRepository bannerDrmRepository) {
        this.bannerRepository = bannerRepository;
        this.bannerTypeRepository = bannerTypeRepository;
        this.bannerDrmRepository = bannerDrmRepository;
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
    public List<BannerData> convertBannerData (List<Banner> banners) {
        List<BannerData> complex =  new ArrayList<>();
        BannerData bannerData = new BannerData();
        for (Banner banner : banners) {
            String bnType = banner.type.getName();
            String bnDrm = null;
            if (banner.drm != null) {
                bnDrm = banner.drm.getName();
            }
            bannerData.setId(banner.getId());
            bannerData.setName(banner.getName());
            bannerData.setPath(banner.getPath());
            bannerData.setType(bnType);
            bannerData.setStatus(banner.getStatus());
            bannerData.setDrm(bnDrm);
            complex.add(bannerData);
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
    public void addBanner(String name, MultipartFile path, int typeId, boolean status, Integer drmId) {
        String pathToBanner = null;
        try {
            pathToBanner = File.separator + Paths.get(bannersLocationPath) + File.separator + path.getOriginalFilename();
            byte[] bytes = path.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(pathToBanner)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        BannerType type = bannerTypeRepository.getOne(typeId);
        BannerDrm drm = null;
        if (drmId != null) {
            drm = bannerDrmRepository.getOne(drmId);
        }
        Banner banner = new Banner();
        banner.setName(name);
        banner.setPath(pathToBanner);
        banner.setType(type);
        banner.setStatus(status);
        banner.setDrm(drm);
        bannerRepository.save(banner);
    }

    public List<BannerType> bannerTypeInPage() {
        return bannerTypeRepository.findAll();
    }

    public List<BannerDrm> bannerDrmInPage() {
        return bannerDrmRepository.findAll();
    }
}
