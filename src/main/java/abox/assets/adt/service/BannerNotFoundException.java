package abox.assets.adt.service;

public class BannerNotFoundException extends RuntimeException {
    private final String bannerID;

    public BannerNotFoundException(int bannerID) {
        this.bannerID = String.valueOf(bannerID);
    }

    public BannerNotFoundException(String type) {
        this.bannerID = type;
    }

    public BannerNotFoundException(boolean status, String drm) {
        this.bannerID = String.valueOf(status) + " " +drm;
    }

    public BannerNotFoundException() {
        this.bannerID = "ALL";
    }

    @Override
    public String getMessage() {
        return "Banner with parameter|s = " + bannerID + " not found";
    }
}
