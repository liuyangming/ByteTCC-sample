package com.bytesvc.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.consumer.service.ITransferService;

@Service("transferServiceCancel")
public class TransferServiceCancel implements ITransferService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void transfer(String sourceAcctId, String targetAcctId, double amount) {
		int value = this.jdbcTemplate.update("update tb_account_two set frozen = frozen - ? where acct_id = ?", amount,
				targetAcctId);
		if (value != 1) {
			throw new IllegalStateException("ERROR!");
		}
		System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", targetAcctId, amount);
	}

}
