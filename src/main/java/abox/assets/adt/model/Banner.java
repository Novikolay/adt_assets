package abox.assets.adt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "banners")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Banner {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "bannersIdSeq", sequenceName = "banners_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bannersIdSeq")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

//    @OneToOne
//    @JoinColumn(name = "type", unique = true, nullable = false)
//    private BannerType type;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "type", nullable = false)
    private BannerType type;

    @Column(name = "status")
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "drm") //, nullable = false
    private BannerDrm drm;

    @Transient
    public String img;

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

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

//    public String getType() {
//        return type;
//    }
//    public void setType(String type) {
//        this.type = type;
//    }

    public BannerType getType() {
        return type;
    }
    public void setType(BannerType type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BannerDrm getDrm() {
        return drm;
    }
    public void setDrm(BannerDrm drm) {
        this.drm = drm;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}