package com.bytesvc.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.consumer.dao.TransferDao;
import com.bytesvc.consumer.service.ITransferService;

@Service("transferServiceConfirm")
public class TransferServiceConfirm implements ITransferService {
	@Autowired
	private TransferDao transferDao;

	@Transactional
	public void transfer(String sourceAcctId, String targetAcctId, double amount) {
		int value = this.transferDao.confirmIncrease(targetAcctId, amount);
		if (value != 1) {
			throw new IllegalStateException("ERROR!");
		}
		System.out.printf("done increase: acct= %s, amount= %7.2f%n", targetAcctId, amount);
	}

}
