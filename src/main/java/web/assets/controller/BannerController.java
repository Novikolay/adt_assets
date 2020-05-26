package web.assets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.assets.model.Banner;
import web.assets.service.BannerService;

import java.util.List;

@RestController
public class BannerController {

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PostMapping(value = "/banners")
    public ResponseEntity<?> create(@RequestBody Banner banner) {
        bannerService.create(banner);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/banners")
    public ResponseEntity<List<Banner>> read() {
        final List<Banner> banners = bannerService.readAll();

        return banners != null &&  !banners.isEmpty()
                ? new ResponseEntity<>(banners, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/banners/{id}")
    public ResponseEntity<Banner> read(@PathVariable(name = "id") int id) {
        final Banner banner = bannerService.read(id);

        return banner != null
                ? new ResponseEntity<>(banner, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/banners/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Banner banner) {
        final boolean updated = bannerService.update(banner, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/banners/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = bannerService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


}
