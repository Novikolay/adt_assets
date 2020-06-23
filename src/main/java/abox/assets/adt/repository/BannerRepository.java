package abox.assets.adt.repository;

import abox.assets.adt.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Integer> {
    Optional<List<Banner>> findByTypeAndStatusAndDrm(String type, boolean status, String drm);
    Optional<List<Banner>> findByTypeNotLikeAndStatusAndDrm(String type, boolean status, String drm);
    Optional<List<Banner>> findByType(String type);
    Optional<List<Banner>> findByTypeNotLike(String type);
}
