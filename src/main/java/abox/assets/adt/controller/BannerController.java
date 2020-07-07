package abox.assets.adt.controller;

import abox.assets.adt.model.BannerData;
import abox.assets.adt.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class BannerController {
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
    public List<BannerData> getBannerMain(
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
    public List<BannerData> getBannerComplex(
            @RequestParam(name = "status") Optional<Boolean> status,
            @RequestParam(name = "drm", required = false) Optional<String> drm) {
        return bannerService.convertBannerData(
                    bannerService.findByTypeNotLike("main", status, drm));
    }

    @PostMapping(value = "/banners/main/{bannerID:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateBannerFileMain(
            @PathVariable int bannerID,
            MultipartFile path
    ) {
        bannerService.update(bannerID, path);
        //bannerMain();
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

    @PostMapping(value = "/banners/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addBanner(
            String name,
            MultipartFile path,
            int type,
            boolean status,
            Integer drm
    ) {
        bannerService.addBanner(name, path, type, status, drm);
    }
}