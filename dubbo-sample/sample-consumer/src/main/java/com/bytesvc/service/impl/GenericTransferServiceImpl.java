package com.bytesvc.service.impl;

import org.bytesoft.compensable.Compensable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.service.IAccountService;
import com.bytesvc.service.ITransferService;

@com.alibaba.dubbo.config.annotation.Service(interfaceClass = ITransferService.class, group = "x-bytetcc", filter = "bytetcc", loadbalance = "bytetcc", cluster = "failfast", retries = 0)
@Service("genericTransferService")
@Compensable(interfaceClass = ITransferService.class, confirmableKey = "transferServiceConfirm", cancellableKey = "transferServiceCancel")
public class GenericTransferServiceImpl implements ITransferService {

	@javax.annotation.Resource(name = "jdbcTemplate2")
	private JdbcTemplate jdbcTemplate;
	@com.alibaba.dubbo.config.annotation.Reference(interfaceClass = IAccountService.class, group = "x-bytetcc", filter = "bytetcc", loadbalance = "bytetcc", cluster = "failfast", retries = 0)
	private IAccountService remoteAccountService;

	@Transactional(rollbackFor = ServiceException.class)
	public void transfer(String sourceAcctId, String targetAcctId, double amount) throws ServiceException {
		this.remoteAccountService.decreaseAmount(sourceAcctId, amount);
		this.increaseAmount(targetAcctId, amount);
	}

	private void increaseAmount(String acctId, double amount) throws ServiceException {
		this.jdbcTemplate.update("update tb_account_two set frozen = frozen + ? where acct_id = ?", amount, acctId);
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
