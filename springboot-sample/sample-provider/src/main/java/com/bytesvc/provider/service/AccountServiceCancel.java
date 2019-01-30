package com.bytesvc.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.interfaces.IAccountService;
import com.bytesvc.provider.model.Account;

@Service("accountServiceCancel")
public class AccountServiceCancel implements IAccountService {
	@Autowired
	private IAccountDao accountDao;

	@Transactional
	public void increaseAmount(String acctId, double amount) {
		Account account = this.accountDao.findById(acctId);
		account.setAmount(account.getAmount() - amount);
		this.accountDao.update(account);
		System.out.printf("undo increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@Transactional
	public void decreaseAmount(String acctId, double amount) {
		Account account = this.accountDao.findById(acctId);
		account.setAmount(account.getAmount() + amount);
		this.accountDao.update(account);
		System.out.printf("undo decrease: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
