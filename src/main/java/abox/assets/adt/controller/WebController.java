package abox.assets.adt.controller;

import abox.assets.adt.service.BannerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class WebController {
    private final BannerService bannerService;

    public WebController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:control";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @GetMapping("/control")
    public String control() {
        return "main/control";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addBanner() {
        return new ModelAndView("main/add")
                .addObject("types", bannerService.bannerTypeInPage())
                .addObject("drms", bannerService.bannerDrmInPage());
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
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

    @RequestMapping(value = "/info", method = RequestMethod.GET)
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

    @PostMapping(value = "/main/{bannerID:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView bannerMainUpdate(
            @PathVariable int bannerID,
            MultipartFile path ) {
        bannerService.update(bannerID, path);
        return bannerMain();
    }

    @PostMapping(value = "/info/{bannerID:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView bannerInfoUpdate(
            @PathVariable int bannerID,
            MultipartFile path ) {
        bannerService.update(bannerID, path);
        return bannerInfo();
    }
}
