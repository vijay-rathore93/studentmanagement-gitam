package com.gitamcollege.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gitamcollege.entity.User;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendingMail(HttpServletRequest request, User userProfileEntity) {
		String appUrl = request.getScheme() + "://" + request.getServerName() + ":"+request.getServerPort();
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(userProfileEntity.getEmail());
		registrationEmail.setSubject("Registration Confirmation");
		registrationEmail.setText("To confirm your e-mail address, please click the link below:\n" + appUrl
				+ "/confirm?token=" + userProfileEntity.getToken());
		 registrationEmail.setFrom("studentlearm@gmail.com");
		System.out.println(registrationEmail.getText());
		mailSender.send(registrationEmail);
	}
}
