package com.kiotfpt.controller;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiotfpt.model.Account;
import com.kiotfpt.model.ResponseObject;
import com.kiotfpt.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
	@Autowired
	private AuthService service;

	@PostMapping("/sign-in")
	public ResponseEntity<ResponseObject> signIn(@RequestBody Account account) {
		return service.signIn(account);
	}

	@GetMapping("")
	public ResponseEntity<ResponseObject> getAll(HttpServletRequest request) throws IOException {
		return service.getAllAccount();
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<ResponseObject> signUp(@RequestBody Account account)
			throws AddressException, MessagingException {
		return service.signUp(account);
	}

//	// check username
//	@PostMapping("/check-username")
//	public ResponseEntity<ResponseObject> checkus(@RequestBody Account account)
//			throws AddressException, MessagingException {
//		return service.checkus(account);
//	}

	@PostMapping("/check-otp-forgot")
	public ResponseEntity<ResponseObject> checkOtpForgot(@RequestBody Map<String, String> obj) {
		return service.checkOtpForgot(obj);
	}

	@PutMapping("/reset-password")
	public ResponseEntity<ResponseObject> resetPassword(@RequestBody Map<String, String> obj) {
		return service.resetPassword(obj);
	}

	@GetMapping("/confirm-sign-up/{id}")
	public String confirmSignup(@PathVariable int id) {
		return service.confirmSignup(id);
	}

	@GetMapping("/sign-out")
	public ResponseEntity<ResponseObject> signOut(HttpServletRequest httpRequest) {
		return service.signOut(httpRequest);
	}
}

