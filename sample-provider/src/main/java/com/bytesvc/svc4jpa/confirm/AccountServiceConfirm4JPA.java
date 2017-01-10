package com.bytesvc.svc4jpa.confirm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.service.IAccountService;
import com.bytesvc.svc4jpa.dao.IAccountDao;

@Service("accountServiceConfirm4JPA")
public class AccountServiceConfirm4JPA implements IAccountService {

	@SuppressWarnings("unused")
	@Autowired
	private IAccountDao accountDao;

	@Transactional(rollbackFor = ServiceException.class)
	public void increaseAmount(String accountId, double amount) throws ServiceException {
		System.out.printf("[jpa] done increase: acct= %s, amount= %7.2f%n", accountId, amount);
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void decreaseAmount(String accountId, double amount) throws ServiceException {
		System.out.printf("[jpa] done decrease: acct= %s, amount= %7.2f%n", accountId, amount);
	}

}
