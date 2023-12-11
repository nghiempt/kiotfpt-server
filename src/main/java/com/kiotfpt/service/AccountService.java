package com.kiotfpt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kiotfpt.model.Account;
import com.kiotfpt.model.AccountProfile;
import com.kiotfpt.model.ResponseObject;
import com.kiotfpt.repository.AccountRepository;
import com.kiotfpt.utils.JsonReader;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repository;
//	@Autowired
//	private JavaMailSender mailSender;
	public String randomNumber;

	HashMap<String, String> responseMessage = new JsonReader().readJsonFile();

//	public ResponseEntity<ResponseObject> getAllAccount(HttpServletRequest request) {
//		String token = "";
//		try {
//			token = request.getHeader("Authorization").split(" ")[1];
//		} catch (NullPointerException e) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(false,
//					HttpStatus.UNAUTHORIZED.toString().split(" ")[0], responseMessage.get("unauthorized"), ""));
//		}
//		Account acc = repository.findByToken(token);
//		if (acc != null) {
//			if (acc.getRole().getID() == 3) {
//				List<Account> accounts = repository.findAll();
//				return !accounts.isEmpty()
//						? ResponseEntity.status(HttpStatus.OK)
//								.body(new ResponseObject(true, HttpStatus.OK.toString().split(" ")[0],
//										"Data has found successfully", accounts))
//						: ResponseEntity.status(HttpStatus.NOT_FOUND)
//								.body(new ResponseObject(false, HttpStatus.NOT_FOUND.toString().split(" ")[0],
//										responseMessage.get("accountNotFound"), ""));
//			} else {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(false,
//						HttpStatus.UNAUTHORIZED.toString().split(" ")[0], responseMessage.get("unauthorized"), ""));
//			}
//		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,
//				HttpStatus.NOT_FOUND.toString().split(" ")[0], responseMessage.get("tokenNotFound"), ""));
//	}

//	public ResponseEntity<ResponseObject> getProfileByAccountID(int id, HttpServletRequest request) {
//		//admin mới vào được
//		String token = new TokenUtils().checkRole(3, request, repository);
//		switch (token) {
//		case "ok":
//			Optional<Account> account = repository.findById(id);
//			if (account.isPresent()) {
//				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,
//						HttpStatus.OK.toString().split(" ")[0], responseMessage.get("profileFound"), account.get().getAccountProfile()));
//			}
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,
//					HttpStatus.NOT_FOUND.toString().split(" ")[0], responseMessage.get("profileNotFound"), ""));
//
//		case "unauthorized":
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(false,
//					HttpStatus.UNAUTHORIZED.toString().split(" ")[0], responseMessage.get("unauthorized"), ""));
//		default:
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,
//					HttpStatus.NOT_FOUND.toString().split(" ")[0], responseMessage.get("tokenNotFound"), ""));
//		}
//	}

	public ResponseEntity<ResponseObject> updateProfile(int id, AccountProfile pro, HttpServletRequest request) {
		Optional<Account> profile = repository.findById(id);
		if (profile.isPresent()) {
			String token = request.getHeader("Authorization").split(" ")[1];
			if (token.equals(profile.get().getToken())) {
				profile.map(Account -> {
					Account.getAccountProfile().setAddress(pro.getAddress());
					Account.getAccountProfile().setAvatar(pro.getAvatar());
					Account.getAccountProfile().setEmail(pro.getEmail());
					Account.getAccountProfile().setName(pro.getName());
					Account.getAccountProfile().setPhone(pro.getPhone());
					return repository.save(Account);
				});
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(true, HttpStatus.OK.toString().split(" ")[0],
								responseMessage.get("updateProfileSuccess"), profile.get().getAccountProfile()));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(false,
						HttpStatus.UNAUTHORIZED.toString().split(" ")[0], responseMessage.get("unauthorized"), ""));
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,
				HttpStatus.NOT_FOUND.toString().split(" ")[0], responseMessage.get("profileNotFound"), ""));
	}

//	public ResponseEntity<ResponseObject> updateWallet(Map<String, String> obj)
//			throws AddressException, MessagingException {
//		Random r = new Random();
//		randomNumber = String.format("%04d", Integer.valueOf(r.nextInt(9999)));
//		Optional<Account> account = repository.findById(Integer.parseInt(obj.get("id")));
//		if (account.isPresent()) {
//			MimeMessage message = mailSender.createMimeMessage();
//			message.setFrom(new InternetAddress("mappe.help@gmail.com"));
//			message.setRecipients(MimeMessage.RecipientType.TO, account.get().getAccountProfile().getEmail());
//			String htmlContent = "";
//			if (obj.get("status").equals("add")) {
//				message.setSubject("Email for add money to wallet");
//				htmlContent = "<h1>OTP code for add money</h1>"
//						+ "<p>Please type this scode to add money to your wallet: </p>" + randomNumber;
//
//			} else {
//				message.setSubject("Email for your payment");
//				htmlContent = "<h1>OTP code for payment</h1>"
//						+ "<p>Please type this scode to confirm your payment: </p>" + randomNumber;
//			}
//			message.setContent(htmlContent, "text/html; charset=utf-8");
//			mailSender.send(message);
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,
//					HttpStatus.OK.toString().split(" ")[0], responseMessage.get("checkMail"), ""));
//		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,
//				HttpStatus.NOT_FOUND.toString().split(" ")[0], responseMessage.get("accountNotFound"), ""));
//	}

	public ResponseEntity<ResponseObject> checkOtpWallet(Map<String, String> obj) {
		Optional<Account> foundAccount = repository.findById(Integer.parseInt(obj.get("id")));
		if (foundAccount.isPresent()) {
			if (obj.get("otpCode").equals(randomNumber)) {
				foundAccount.get().setWallet(foundAccount.get().getWallet() + Double.parseDouble(obj.get("wallet")));
				repository.save(foundAccount.get());
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,
						HttpStatus.OK.toString().split(" ")[0], responseMessage.get("updateWalletSuccess"), ""));
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,
				HttpStatus.NOT_FOUND.toString().split(" ")[0], responseMessage.get("accountNotFound"), ""));
	}

	public ResponseEntity<ResponseObject> checkOtpPayment(Map<String, String> obj) {
		Optional<Account> acc = repository.findById(Integer.parseInt(obj.get("id")));
		Double money = Double.parseDouble(obj.get("wallet"));
		if (acc.isPresent()) {
			if (obj.get("otpCode").equals(randomNumber)) {
				if (money < 0 && acc.get().getWallet() > -money) {
					acc.get().setWallet(acc.get().getWallet() + money);
					repository.save(acc.get());
					return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,
							HttpStatus.OK.toString().split(" ")[0], responseMessage.get("paymentSuccess"), ""));
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(false,
							HttpStatus.BAD_REQUEST.toString().split(" ")[0], responseMessage.get("paymentFailed"), ""));
				}
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,
				HttpStatus.NOT_FOUND.toString().split(" ")[0], responseMessage.get("accountNotFound"), ""));
	}
}
