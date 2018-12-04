package com.bytesvc.provider.controller;

import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytesvc.provider.service.IAccountService;

@Compensable(interfaceClass = IAccountService.class, cancellableKey = "accountServiceCancel")
@RestController
public class AccountController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ResponseBody
	@RequestMapping(value = "/increase", method = RequestMethod.POST)
	@Transactional
	public void increaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {
		this.jdbcTemplate.update("update tb_account_one set amount = amount + ? where acct_id = ?", amount, acctId);
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@ResponseBody
	@RequestMapping(value = "/decrease", method = RequestMethod.POST)
	@Transactional
	public void decreaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {
		this.jdbcTemplate.update("update tb_account_one set amount = amount - ? where acct_id = ?", amount, acctId);
		System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);

		// throw new IllegalStateException("error");
	}

}
