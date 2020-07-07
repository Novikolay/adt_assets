package abox.assets.adt.repository;

import abox.assets.adt.model.BannerDrm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerDrmRepository extends JpaRepository<BannerDrm, Integer> {
    List<BannerDrm> findByName(String name);
}