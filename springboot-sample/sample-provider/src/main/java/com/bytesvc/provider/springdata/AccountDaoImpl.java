package com.bytesvc.provider.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.model.Account;

@Component("accountDao")
public class AccountDaoImpl implements IAccountDao {
	@Autowired
	private AccountRepository repository;

	public Account findById(String identifier) {
		return this.repository.findById(identifier).get();
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
