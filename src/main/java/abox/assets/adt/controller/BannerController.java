package abox.assets.adt.controller;

import abox.assets.adt.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class BannerController {

    @Value("${bannersLocationPath}")
    private String bannersLocationPath;

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    /** Получение основного информационного банера
     * Пример запроса: http://localhost:8080/banner/main?status=true&drm=L1
     * @param status    статус услуги (подключена == true, не подключена == false), обязательный параметр
     * @param drm       уровень защиты (если есть == L1/L2/L3), не обязательный параметр
     * @return
     */
    @GetMapping("/banner/main")
    @ResponseBody
    public List<Object[]> getBannerMain(
            @RequestParam(name = "status") Optional<Boolean> status,
            @RequestParam(name = "drm", required = false) Optional<String> drm) {
        return bannerService.convertBannerData(
                    bannerService.findByType("main", status, drm));
    }

    /** Получение двух комплектов вспомогательных информационных банеров (массивом)
     * Пример запроса: http://localhost:8080/banner/complex?status=false&drm=L2
     * @param status    статус услуги (подключена == true, не подключена == false), обязательный параметр
     * @param drm       уровень защиты (если есть == L1/L2/L3), не обязательный параметр
     * @return
     */
    @GetMapping("/banner/complex")
    @ResponseBody
    public List<Object[]> getBannerComplex(
            @RequestParam(name = "status") Optional<Boolean> status,
            @RequestParam(name = "drm", required = false) Optional<String> drm) {
        return bannerService.convertBannerData(
                    bannerService.findByTypeNotLike("main", status, drm));
    }

    @RequestMapping(value = "/banners/main", method = RequestMethod.GET)
    public ModelAndView bannerMain() {
        return new ModelAndView("main/main")
                .addObject("files",
                        bannerService.changeBannerData(
                                bannerService.findByType(
                                        "main",
                                        Optional.empty(),
                                        Optional.empty()
                                )));
    }

    @RequestMapping(value = "/banners/info", method = RequestMethod.GET)
    public ModelAndView bannerInfo() {
        return new ModelAndView("main/info")
                .addObject("files",
                        bannerService.changeBannerData(
                                bannerService.findByTypeNotLike(
                                        "main",
                                        Optional.empty(),
                                        Optional.empty()
                                )));
    }

    @PostMapping(value = "/banners/main/{bannerID:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateBannerFileMain(
            @PathVariable int bannerID,
            MultipartFile path
    ) {
        bannerService.update(bannerID, path);
        bannerMain();
        return "Done";
    }

    @PostMapping(value = "/banners/info/{bannerID:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateBannerFileInfo(
            @PathVariable int bannerID,
            MultipartFile path
    ) {
        bannerService.update(bannerID, path);
        return "Done";
    }

//    @RequestMapping(value = "/banners/update/{bannerID:\\d+}", method = RequestMethod.GET)
//    public ModelAndView bannerUpdate(@PathVariable(name = "bannerID") int bannerID) {
//        List<Banner> banners = bannerService.getBannerAll();
//        Banner banner = banners.get(bannerID);
//        ModelAndView mav = new ModelAndView("main/update");
//        mav.addObject("file", banner);
//        return mav;
//    }

//    @RequestMapping(value = "/banners/main/update", method = RequestMethod.GET)
//    public ModelAndView bannerMainUpdated() {
//        List<Banner> banners = bannerService.getBannerByType("main");
//        int i = 0;
//        for(Banner banner : banners){
//            String path = banner.getPath();
////            System.out.println(bannersLocationPath);
//            String img = path.replace(bannersLocationPath, "").replace("//", "/testbanners/");
////            System.out.println(img);
//            banner.setIMG(img);
//            banners.set(i, banner);
//            i++;
//        }
//        ModelAndView mav = new ModelAndView("main/main"); //::update_banner
//        mav.addObject("files", banners);
//        return mav;
//    }
}