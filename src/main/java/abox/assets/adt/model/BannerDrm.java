package abox.assets.adt.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "drms")
class BannerDrm {
    @Id
    @Column(name = "id")
    private Integer id;

    //@Column(name = "name")
//    @OneToOne(mappedBy = "drm")
//    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "drm")
    private Set<Banner> drm;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}