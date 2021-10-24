package com.gitamcollege.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gitamcollege.UserRepo;

@Controller
public class StudentUIController {

	@Autowired
	private UserRepo userRepo;

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

	@GetMapping("/{token}/all")
	public String getAllData(@PathVariable("token") String token) {
		if (userRepo.findByToken(token).isPresent())
			return "dashboardAdmin";
		else {
			return "notFound";
		}
	}

	@GetMapping("/{token}/students")
	public String getStudentData(@PathVariable("token") String token) {
		if (userRepo.findByToken(token).isPresent())
			return "dashboardStudent";
		else {
			return "notFound";
		}

	}

	@GetMapping("/resetPassword")
	public ModelAndView getReset(@RequestParam("token") String token) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("resetPassword");
		modelAndView.addObject("token", token);
		return modelAndView;
	}

}
