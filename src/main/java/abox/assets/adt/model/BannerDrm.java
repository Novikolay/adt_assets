package abox.assets.adt.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "drms")
class BannerDrm {
    @Id
    @Column(name = "id")
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "drm")
    private List<Banner> banners;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public List<Banner> getName() {
        return banners;
    }
    public void setName(List<Banner> banners) {
        this.banners = banners;
    }

}