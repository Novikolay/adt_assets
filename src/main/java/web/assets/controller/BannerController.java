package web.assets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import web.assets.model.Banner;
//import web.assets.repository.BannerRepository;
import web.assets.request.BannerRequest;
import web.assets.service.BannerService;
//import web.assets.service.BannerSpecification;
//import web.assets.service.BannerSpecificationsBuilder;
//import web.assets.service.BannerSpecificationsBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping(value = "/banner", produces = MediaType.APPLICATION_JSON_VALUE)
public class BannerController {

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping(value = "/banner/{bannerID:\\d+}")
    public Banner getProfile(@PathVariable int bannerID) {
        return bannerService.getBanner(bannerID);
    }

    /** Получение основного информационного банера
     * Пример запроса: http://localhost:8080/banner/main/true&L1
     * @param status    -- статус услуги (подключена == true, не подключена == false), обязательный параметр
     * @param drm       -- уровень защиты (если есть == L1/L2/L3), не обязательный параметр
     * @return
     */
    @GetMapping(value = "/banner/main/{status}&{drm}")
    public Banner getBannerMain(@PathVariable(name = "status") boolean status, @PathVariable(name = "drm") String drm) {
        return bannerService.getBannerMain(status, drm);
    }

    /** Получение двух комплектов вспомогательных информационных банеров (массивом)
     * Пример запроса: http://localhost:8080/banner/complex/true&L2
     * @param status    -- статус услуги (подключена == true, не подключена == false), обязательный параметр
     * @param drm       -- уровень защиты (если есть == L1/L2/L3), не обязательный параметр
     * @return
     */
    @GetMapping(value = "/banner/complex/{status}&{drm}")
    public List<Banner> getBannerComplex(@PathVariable(name = "status") boolean status, @PathVariable(name = "drm") String drm) {
        return bannerService.getBannerComplex(status, drm);
    }

    @RequestMapping(value = "/banner/main", method = RequestMethod.GET)
    public ModelAndView bannerMain() {
        List<Banner> bannerInfo = bannerService.getBannerByType("main");
        ModelAndView mav = new ModelAndView("main/main");
        mav.addObject("files", bannerInfo);
        return mav;
    }

    @RequestMapping(value = "/banner/info", method = RequestMethod.GET)
    public ModelAndView bannerInfo() {
        List<Banner> bannerInfo = bannerService.getBannerNotByType("main");
        ModelAndView mav = new ModelAndView("main/info");
        mav.addObject("files", bannerInfo);
        return mav;
    }

    //updateBannerFile(String path, int id)
//    @GetMapping(value = "/banner/{bannerID:\\d+}")
//    @GetMapping(value = "/{personId:\\d+}")

//    @PostMapping(value = "/banner/main/{bannerID:\\d+}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateBannerFile(
//            //@Valid @RequestBody BannerRequest request,
//            @RequestBody BannerRequest request,
//            @PathVariable int bannerID
//    ) {
//        bannerService.updateBannerFile(
//                request.getPath(),
//                bannerID
//        );
//    }

//    @RequestMapping(value = "/banner/main/{bannerID:\\d+}", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateBannerFile(
//            //@Valid @RequestBody BannerRequest request,
//            @RequestBody BannerRequest request,
//            @PathVariable int bannerID
//    ) {
//        bannerService.updateBannerFile(
//                request.getPath(),
//                bannerID
//        );
//    }

//    @PostMapping("/banner/main")
//    public String bannerUpdateSubmit(@RequestParam("updatedbanner") Banner banner, Model model) {

    @RequestMapping(value="/banner/main", method=RequestMethod.POST)
    public Banner bannerUpdateSubmit(@ModelAttribute Banner banner, Model model) {
        model.addAttribute("file", banner);
        return banner;
    }

}

//    private final BannerService bannerService;
//    //private final BannerRepository bannerRepository;
//
//    @Autowired
//    public BannerController(BannerService bannerService) { //, BannerRepository bannerRepository
//        this.bannerService = bannerService;
//        //this.bannerRepository = bannerRepository;
//    }
//
////
////    @PutMapping(value = "/banners/{id}")
////    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Banner banner) {
////        final boolean updated = bannerService.update(banner, id);
////
////        return updated
////                ? new ResponseEntity<>(HttpStatus.OK)
////                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
////    }
