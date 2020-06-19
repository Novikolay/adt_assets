package abox.assets.adt.dao;

import abox.assets.adt.model.Banner;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface BannerDao {
    Optional<Banner> getBannerById(int id);
    Optional<List<Banner>> getBannerByType(String type);
    Optional<List<Banner>> getBannerNotByType(String type);
    Optional<Banner> getBannerMain(boolean status, String drm);
    Optional<List<Banner>> getBannerComplex(boolean status, String drm);
    Optional<List<Banner>> getBannerAll();
    void updateBannerFile(String path, int id);
}
