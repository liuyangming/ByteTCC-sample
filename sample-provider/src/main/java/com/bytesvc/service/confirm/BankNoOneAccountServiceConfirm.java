package com.bytesvc.service.confirm;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.service.IAccountService;

@Service("accountServiceConfirm")
public class BankNoOneAccountServiceConfirm implements IAccountService {

	@SuppressWarnings("restriction")
	@javax.annotation.Resource(name = "dataSource")
	private DataSource dataSource;

	@Transactional(rollbackFor = ServiceException.class)
	public void increaseAmount(String accountId, double amount) throws ServiceException {
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void decreaseAmount(String accountId, double amount) throws ServiceException {
	}

}
