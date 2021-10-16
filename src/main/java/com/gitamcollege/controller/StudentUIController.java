package com.gitamcollege.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

	
	@GetMapping("/reset")
	public String getReset() {
		return "resetPassword";
	}
	
}
