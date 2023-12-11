package com.kiotfpt.utils;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.kiotfpt.model.Account;
import com.kiotfpt.repository.AccountRepository;

@Service
public class TokenUtils {

	public TokenUtils() {
	}

//	public String checkRole(int role_id, HttpServletRequest request, AccountRepository repo) {
//		if (request.getHeader("Authorization") == null) {
//			return "empty";
//		}
//
//		String token = request.getHeader("Authorization").split(" ")[1];
//		Account acc = repo.findByToken(token);
//		if (acc != null) {
//			if (acc.getRole().getID() == role_id) {
//				return "ok";
//			} else {
//				return "unauthorized";
//			}
//		} else {
//			return "notFound";
//		}
//	}

	public String checkMatch(int account_id, HttpServletRequest request, AccountRepository repo) {
		if (request.getHeader("Authorization") == null) {
			return "empty";
		}
		String token = request.getHeader("Authorization").split(" ")[1];
		Account acc = repo.findByToken(token);
		if (acc != null) {
			if (acc.getID() == account_id) {
				return "ok";
			} else {
				return "unauthorized";
			}

		} else {
			return "notFound";
		}
	}

}
