package com.kiotfpt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiotfpt.model.AccountProfile;
import com.kiotfpt.model.ResponseObject;
import com.kiotfpt.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class AccountController {
	@Autowired
	private AccountService service;

//	@GetMapping("/account")
//	public ResponseEntity<ResponseObject> getAllAccount(HttpServletRequest request) {
//		return service.getAllAccount(request);
//	}

//	@GetMapping("/account/profile")
//	public ResponseEntity<ResponseObject> getProfileByAccountID(@RequestParam(value = "ID", required = false) int id, HttpServletRequest request) {
//		return service.getProfileByAccountID(id, request);
//	}

	@PutMapping("/profile/update/{id}")
	public ResponseEntity<ResponseObject> updateProfile(@PathVariable int id, @RequestBody AccountProfile profile,
			HttpServletRequest request) {
		return service.updateProfile(id, profile, request);
	}

//	@PostMapping("/account/update-wallet")
//	public ResponseEntity<ResponseObject> updateWallet(@RequestBody Map<String, String> obj)
//			throws AddressException, MessagingException {
//		return service.updateWallet(obj);
//	}

	@PutMapping("/account/check-otp-add-money-to-wallet")
	public ResponseEntity<ResponseObject> checkOtpWallet(@RequestBody Map<String, String> obj) {
		return service.checkOtpWallet(obj);
	}

	@PutMapping("/account/check-otp-payment")
	public ResponseEntity<ResponseObject> checkOtpPayment(@RequestBody Map<String, String> obj) {
		return service.checkOtpPayment(obj);
	}
}
