package abox.assets.adt.repository;

import abox.assets.adt.model.Banner;
import abox.assets.adt.model.BannerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Integer> {
    @Query("from Banner where type.name = :type and status = :status and drm.name = :drm")
    Optional<List<Banner>> findByTypeAndStatusAndDrm(String type, boolean status, String drm);
    @Query("from Banner where type.name not like :type and status = :status and drm.name = :drm")
    Optional<List<Banner>> findByTypeNotLikeAndStatusAndDrm(String type, boolean status, String drm);
    @Query("from Banner where type.name = :type and status = :status and drm is null")
    Optional<List<Banner>> findByTypeAndStatus(String type, boolean status);
    @Query("from Banner where type.name not like :type and status = :status and drm is null")
    Optional<List<Banner>> findByTypeNotLikeAndStatus(String type, boolean status);
    @Query("from Banner where type.name = :type")
    Optional<List<Banner>> findByType(String type);
    @Query("from Banner where type.name not like :type")
    Optional<List<Banner>> findByTypeNotLike(String type);
}