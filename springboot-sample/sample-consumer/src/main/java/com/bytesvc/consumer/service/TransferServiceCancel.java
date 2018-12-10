package com.bytesvc.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.consumer.interfaces.ITransferService;

@Service("transferServiceCancel")
public class TransferServiceCancel implements ITransferService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void transferAmount(String source, String target, double amount) {
		this.jdbcTemplate.update("update tb_account_two set amount = amount - ? where acct_id = ?", amount, target);
		System.out.printf("undo transfer: source= %s, target= %s, amount= %7.2f%n", source, target, amount);
	}

}
