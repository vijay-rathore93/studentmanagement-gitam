package com.gitamcollege.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gitamcollege.UserRepo;
import com.gitamcollege.entity.User;
import com.gitamcollege.service.EmailService;

@RestController
public class StudentDataController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailService emailService;

	@PostMapping("/student")
	public User saveUser(@RequestBody User user, HttpServletRequest request) {
		
		if(userRepo.existsByEmail(user.getEmail()))
			throw new RuntimeException("Email Exists");
		
		
		
		user.setToken(UUID.randomUUID().toString());
		user.setIsActive(false);
		User userCreated = userRepo.save(user);
		emailService.sendingMail(request, userCreated);
		return userCreated;

	}

	@GetMapping("/confirm")
	public User saveUser(@RequestParam("token") String token) {
		User user = userRepo.findByToken(token).orElseThrow(() -> new RuntimeException("Token not found"));
		user.setToken(null);
		user.setIsActive(true);
		User userCreated = userRepo.save(user);
		return userCreated;

	}

}
