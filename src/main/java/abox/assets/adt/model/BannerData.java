package abox.assets.adt.model;

public class BannerData {
    private int id;
    private String name;
    private String path;
    private String type;
    private boolean status;
    private String drm;

    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) { this.type = type; }

    public String getDrm() { return drm; }
    public void setDrm(String drm) { this.drm = drm; }
}