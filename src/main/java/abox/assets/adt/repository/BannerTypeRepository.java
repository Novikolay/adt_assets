package abox.assets.adt.repository;

import abox.assets.adt.model.BannerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerTypeRepository extends JpaRepository<BannerType, Integer> {
    List<BannerType> findByName(String name);
}