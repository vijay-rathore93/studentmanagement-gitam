package com.gitamcollege.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		if (userRepo.existsByEmail(user.getEmail()))
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
	
	@PostMapping("/changePassword")
	public User saveUser(@RequestBody User user) {
		User userFound = userRepo.findByToken(user.getToken()).orElseThrow(() -> new RuntimeException("Token not found"));
		userFound.setPassword(user.getPassword());
		User userCreated = userRepo.save(userFound);
		return userCreated;

	}

	@PostMapping("/sendLink")
	public User sendLink(@RequestBody User userInput, HttpServletRequest request) {
		User user = userRepo.findByEmail(userInput.getEmail())
				.orElseThrow(() -> new RuntimeException("Email not found"));
		user.setToken(UUID.randomUUID().toString());
		user.setIsActive(false);
		User userCreated = userRepo.save(user);
		emailService.forgetPassword(request, userCreated);
		return userCreated;

	}

}
