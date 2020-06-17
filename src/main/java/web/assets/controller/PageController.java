package web.assets.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

	@GetMapping("/")
	public String root() {
		return "redirect:banner/control";
	}

	@GetMapping("/login") //login
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