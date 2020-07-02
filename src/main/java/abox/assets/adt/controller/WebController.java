package abox.assets.adt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
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
}
