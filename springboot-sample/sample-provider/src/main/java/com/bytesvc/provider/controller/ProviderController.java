package com.bytesvc.provider.controller;

import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.interfaces.IAccountService;
import com.bytesvc.provider.model.Account;

@Compensable(interfaceClass = IAccountService.class, cancellableKey = "accountServiceCancel")
@RestController
public class ProviderController {
	@Autowired
	private IAccountDao accountDao;

	@ResponseBody
	@RequestMapping(value = "/increase/{acctId}/{amount}", method = RequestMethod.POST)
	@Transactional
	public void increaseAmount(@PathVariable("acctId") String acctId, @PathVariable("amount") double amount) {
		Account account = this.accountDao.findById(acctId);
		account.setAmount(account.getAmount() + amount);
		this.accountDao.update(account);
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@ResponseBody
	@RequestMapping(value = "/decrease/{acctId}/{amount}", method = RequestMethod.POST)
	@Transactional
	public void decreaseAmount(@PathVariable("acctId") String acctId, @PathVariable("amount") double amount) {
		Account account = this.accountDao.findById(acctId);
		account.setAmount(account.getAmount() - amount);
		this.accountDao.update(account);
		System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
