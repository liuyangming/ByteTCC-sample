package com.bytesvc.svc4jpa.dao;

import org.springframework.stereotype.Component;

import com.bytesvc.service.repository.AccountRepository;
import com.bytesvc.svc4jpa.model.Account;

@Component("accountDao4SpringDataJpa")
public class AccountDao4SpringDataJpa implements IAccountDao {
	@SuppressWarnings("restriction")
	@javax.annotation.Resource(name = "accountRepository")
	private AccountRepository repository;

	public Account findById(String identifier) {
		return this.repository.findOne(identifier);
	}

	public void insert(Account account) {
		this.repository.saveAndFlush(account);
	}

	public void update(Account account) {
		this.repository.saveAndFlush(account);
	}

	public void delete(Account account) {
		this.repository.delete(account);
	}

}
