package web.assets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.assets.model.Banner;
import web.assets.repository.BannerRepository;
import web.assets.service.BannerService;
import web.assets.service.BannerSpecification;
//import web.assets.service.BannerSpecificationsBuilder;
import web.assets.service.BannerSpecificationsBuilder;
import web.assets.service.SearchCriteria;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class BannerController {

    private final BannerService bannerService;
    private final BannerRepository bannerRepository;

    @Autowired
    public BannerController(BannerService bannerService, BannerRepository bannerRepository) {
        this.bannerService = bannerService;
        this.bannerRepository = bannerRepository;
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

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }

    @RequestMapping("/banners/main")
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        BannerSpecification spec =
                new BannerSpecification(new SearchCriteria("type", ":", "main"));

        List<Banner> results = bannerRepository.findAll(spec);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/banner")
    @ResponseBody
    public List<Banner> search(@RequestParam(value = "search") String search) { //http://localhost:8080/banner?search=type:main,age>25
        BannerSpecificationsBuilder builder = new BannerSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Banner> spec = builder.build();
        return bannerRepository.findAll(spec);
    }

}
