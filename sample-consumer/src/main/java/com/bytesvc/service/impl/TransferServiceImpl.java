package com.bytesvc.service.impl;

import org.bytesoft.compensable.Compensable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.service.IAccountService;
import com.bytesvc.service.ITransferService;

@Service("transferService")
@Compensable(interfaceClass = ITransferService.class)
public class TransferServiceImpl implements ITransferService {

	@SuppressWarnings("restriction")
	@javax.annotation.Resource(name = "accountService")
	private IAccountService nativeAccountService;
	@SuppressWarnings("restriction")
	@javax.annotation.Resource(name = "remoteAccountService")
	private IAccountService remoteAccountService;

	@Transactional(rollbackFor = ServiceException.class)
	public void transfer(String sourceAcctId, String targetAcctId, double amount) throws ServiceException {
		this.nativeAccountService.increaseAmount(targetAcctId, amount);
		this.remoteAccountService.decreaseAmount(sourceAcctId, amount);

		// throw new ServiceException("rollback");
	}

}
