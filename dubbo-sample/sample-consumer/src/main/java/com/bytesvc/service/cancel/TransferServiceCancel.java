package com.bytesvc.service.cancel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.service.ITransferService;

@Service("transferServiceCancel")
public class TransferServiceCancel implements ITransferService {

	@javax.annotation.Resource(name = "jdbcTemplate2")
	private JdbcTemplate jdbcTemplate;

	@Transactional(rollbackFor = ServiceException.class)
	public void transfer(String sourceAcctId, String targetAcctId, double amount) throws ServiceException {
		this.jdbcTemplate.update("update tb_account_two set frozen = frozen - ? where acct_id = ?", amount, targetAcctId);
		System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", targetAcctId, amount);
	}

}
