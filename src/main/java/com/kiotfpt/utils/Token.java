package com.kiotfpt.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;
@Service
public class Token {
    public Token() {
    }

    
	// this function to generate token
	public static String generateToken() {
		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder(8);
		SecureRandom random = new SecureRandom();
		while (sb.length() < 8) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(randomIndex));
		}
		return sb.toString();
	}
}
