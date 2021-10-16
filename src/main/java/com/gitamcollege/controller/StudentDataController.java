package com.gitamcollege.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gitamcollege.UserRepo;
import com.gitamcollege.entity.User;

@RestController
public class StudentDataController {

	@Autowired
	private UserRepo userRepo;

	@PostMapping("/student")
	public User saveUser(@RequestBody User user) {
		return userRepo.save(user);
	}

}
