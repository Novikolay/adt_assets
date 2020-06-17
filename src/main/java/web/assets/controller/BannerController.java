package web.assets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import web.assets.model.Banner;
import web.assets.request.BannerRequest;
import web.assets.service.BannerService;
import web.assets.service.BannerServiceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

@RestController
//@RequestMapping(value = "/banner", produces = MediaType.APPLICATION_JSON_VALUE)
public class BannerController {

    @Value("${bannersLocationPath}")
    private String bannersLocationPath;

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
        List<Banner> banners = bannerService.getBannerByType("main");
        int i = 0;
        for(Banner banner : banners){
            String path = banner.getPath();
            System.out.println(bannersLocationPath);
            String img = path.replace(bannersLocationPath, "").replace("//", "/testbanners/");
            System.out.println(img);
            banner.setIMG(img);
            banners.set(i, banner);
            i++;
        }
        ModelAndView mav = new ModelAndView("main/main");
        mav.addObject("files", banners);
        return mav;
    }

    @RequestMapping(value = "/banner/info", method = RequestMethod.GET)
    public ModelAndView bannerInfo() {
        List<Banner> bannerInfo = bannerService.getBannerNotByType("main");
        ModelAndView mav = new ModelAndView("main/info");
        mav.addObject("files", bannerInfo);
        return mav;
    }

    @PostMapping(value = "/banner/main/{bannerID:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateBannerFile(
            @PathVariable int bannerID,
            MultipartFile path
    ) {
        bannerService.storeBanner(bannerID, path);
        System.out.println("bannerID: " + bannerID);
        System.out.println("path: " + path);
        System.out.println("OriginalFilename: " + path.getOriginalFilename());
        return "Done";
    }
}