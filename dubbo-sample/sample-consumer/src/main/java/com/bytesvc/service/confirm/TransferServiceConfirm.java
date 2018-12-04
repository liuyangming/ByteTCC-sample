package com.bytesvc.service.confirm;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.service.ITransferService;

@Service("transferServiceConfirm")
public class TransferServiceConfirm implements ITransferService {

	@javax.annotation.Resource(name = "jdbcTemplate2")
	private JdbcTemplate jdbcTemplate;

	@Transactional(rollbackFor = ServiceException.class)
	public void transfer(String sourceAcctId, String targetAcctId, double amount) throws ServiceException {
		this.jdbcTemplate.update("update tb_account_two set amount = amount + ?, frozen = frozen - ? where acct_id = ?", amount,
				amount, targetAcctId);
		System.out.printf("done increase: acct= %s, amount= %7.2f%n", targetAcctId, amount);
	}

}
