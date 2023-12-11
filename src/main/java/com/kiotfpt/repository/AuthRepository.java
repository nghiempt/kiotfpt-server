package com.kiotfpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiotfpt.model.Account;

@Repository
public interface AuthRepository extends JpaRepository<Account, Integer> {
	Account findByUsername(String userName);
	Account findByToken (String token);
}
