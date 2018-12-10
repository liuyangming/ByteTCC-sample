package com.bytesvc.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.provider.interfaces.IAccountService;

@Service("accountServiceConfirm")
public class AccountServiceConfirm implements IAccountService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void increaseAmount(String acctId, double amount) {
		this.jdbcTemplate.update("update tb_account_one set amount = amount - ? where acct_id = ?", amount, acctId);
		System.out.printf("done increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@Transactional
	public void decreaseAmount(String acctId, double amount) {
		this.jdbcTemplate.update("update tb_account_one set amount = amount + ? where acct_id = ?", amount, acctId);
		System.out.printf("done decrease: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
