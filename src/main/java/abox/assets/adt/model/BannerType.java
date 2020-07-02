package abox.assets.adt.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "types")
class BannerType {
    @Id
    @Column(name = "id")
    private Integer id;

    //@Column(name = "name")
    //@OneToOne(mappedBy = "type")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private Set<Banner> banner;
    //private String name;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}