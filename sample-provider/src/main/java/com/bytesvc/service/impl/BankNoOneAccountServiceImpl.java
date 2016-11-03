package com.bytesvc.service.impl;

import javax.sql.DataSource;

import org.bytesoft.compensable.Compensable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.service.IAccountService;

@Service("accountService")
@Compensable( //
interfaceClass = IAccountService.class//
, confirmableKey = "accountServiceConfirm" //
, cancellableKey = "accountServiceCancel" //
)
public class BankNoOneAccountServiceImpl implements IAccountService {

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
