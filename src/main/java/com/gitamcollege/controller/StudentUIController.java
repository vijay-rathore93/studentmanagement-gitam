package com.gitamcollege.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentUIController {

	@GetMapping({ "/", "/login" })
	public String getLogin() {
		return "login";
	}

	@GetMapping("/forget")
	public String getForgetPage() {
		return "forget";
	}

	@GetMapping("/signUp")
	public String getsignUp() {
		return "signup";
	}

	@GetMapping("/resetPassword")
	public ModelAndView getReset(@RequestParam("token") String token) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("resetPassword");
		modelAndView.addObject("token", token);
		return modelAndView;
	}

}
