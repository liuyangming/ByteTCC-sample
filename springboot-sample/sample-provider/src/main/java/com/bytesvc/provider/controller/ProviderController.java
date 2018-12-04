package com.bytesvc.provider.controller;

import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytesvc.provider.interfaces.IAccountService;

@Compensable(interfaceClass = IAccountService.class, confirmableKey = "accountServiceConfirm", cancellableKey = "accountServiceCancel")
@RestController
public class ProviderController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ResponseBody
	@RequestMapping(value = "/increase/{acctId}/{amount}", method = RequestMethod.POST)
	@Transactional
	public void increaseAmount(@PathVariable("acctId") String acctId, @PathVariable("amount") double amount) {
		this.jdbcTemplate.update("update tb_account_one set amount = amount + ? where acct_id = ?", amount, acctId);
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@ResponseBody
	@RequestMapping(value = "/decrease/{acctId}/{amount}", method = RequestMethod.POST)
	@Transactional
	public void decreaseAmount(@PathVariable("acctId") String acctId, @PathVariable("amount") double amount) {
		int value = this.jdbcTemplate.update("update tb_account_one set amount = amount - ? where acct_id = ?", amount, acctId);
		if (value != 1) {
			throw new IllegalStateException("ERROR!");
		}
		System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
