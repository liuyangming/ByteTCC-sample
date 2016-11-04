package com.bytesvc.service.confirm;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.service.IAccountService;

@Service("accountServiceConfirm")
public class BankNoTwoAccountServiceConfirm implements IAccountService {

	@SuppressWarnings("restriction")
	@javax.annotation.Resource(name = "dataSource")
	private DataSource dataSource;

	@Transactional(rollbackFor = ServiceException.class)
	public void increaseAmount(String accountId, double amount) throws ServiceException {
		System.out.printf("done increase: acct= %s, amount= %7.2f%n", accountId, amount);
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void decreaseAmount(String accountId, double amount) throws ServiceException {
		System.out.printf("done decrease: acct= %s, amount= %7.2f%n", accountId, amount);
	}

}
