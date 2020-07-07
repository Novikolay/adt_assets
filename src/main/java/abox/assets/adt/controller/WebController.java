package abox.assets.adt.controller;

import abox.assets.adt.service.BannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        return "redirect:banner/control";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @GetMapping("/banner/control")
    public String control() {
        return "main/control";
    }

    @GetMapping("/control")
    public String control2() {
        return "main/control";
    }

    @RequestMapping(value = "/banners/add", method = RequestMethod.GET)
    public ModelAndView addBanner() {
        return new ModelAndView("main/add")
                .addObject("types", bannerService.bannerTypeInPage())
                .addObject("drms", bannerService.bannerDrmInPage());
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

}
