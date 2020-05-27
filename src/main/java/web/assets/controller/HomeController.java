package web.assets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    @GetMapping("/")
//    public String root() {
//        return "index";
//        //return "login";
//    }

//    @GetMapping("/main")
//    public String userIndex() {
//        return "main/listfiles";
//    }

//    @GetMapping("/user")
////    public String userIndex() {
////        return "user/index";
////    }
//    public String userIndex() {
//        return "upload";
//    }

    @GetMapping("/") //login
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}
