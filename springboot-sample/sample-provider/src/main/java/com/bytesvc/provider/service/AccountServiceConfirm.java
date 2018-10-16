package com.bytesvc.provider.service;

import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.provider.interfaces.IAccountService;

@Service("accountServiceConfirm")
public class AccountServiceConfirm implements IAccountService, CompensableContextAware {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private CompensableContext context;

	@Transactional
	public void increaseAmount(String acctId, double amount) {
//		int value = this.jdbcTemplate.update("update tb_account_one set amount = amount - ? where acct_id = ?", amount, acctId);
//		if (value != 1) {
//			throw new IllegalStateException("ERROR!");
//		}
		System.out.println(this.context.getVariable("status1"));
		System.out.println(this.context.getVariable("status2"));
		System.out.printf("done increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@Transactional
	public void decreaseAmount(String acctId, double amount) {
		int value = this.jdbcTemplate.update("update tb_account_one set amount = amount + ? where acct_id = ?", amount, acctId);
		if (value != 1) {
			throw new IllegalStateException("ERROR!");
		}
		System.out.printf("done decrease: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	public void setCompensableContext(CompensableContext aware) {
		this.context = aware;
	}

}
