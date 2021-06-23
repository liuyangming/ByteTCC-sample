package com.bytesvc.consumer.controller;

import org.bytesoft.compensable.Compensable;
import org.bytesoft.compensable.CompensableCancel;
import org.bytesoft.compensable.CompensableConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytesvc.consumer.dao.TransferDao;
import com.bytesvc.consumer.service.ITransferService;
import com.bytesvc.feign.service.IAccountService;

@Compensable(interfaceClass = ITransferService.class, simplified = true)
@RestController
public class SimplifiedController implements ITransferService {
	@Autowired
	private TransferDao transferDao;

	@Autowired
	private IAccountService acctService;

	@ResponseBody
	@RequestMapping(value = "/simplified/transfer", method = RequestMethod.POST)
	@Transactional
	public void transfer(@RequestParam String sourceAcctId, @RequestParam String targetAcctId, @RequestParam double amount) {
		this.acctService.decreaseAmount(sourceAcctId, amount);
		this.increaseAmount(targetAcctId, amount);
	}

	private void increaseAmount(String acctId, double amount) {
		this.transferDao.increaseAmount(acctId, amount);
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@CompensableCancel
	@Transactional
	public void cancelTransfer(String sourceAcctId, String targetAcctId, double amount) {
		this.transferDao.cancelIncrease(targetAcctId, amount);
		System.out.printf("undo decrease: acct= %s, amount= %7.2f%n", targetAcctId, amount);
	}

	/**
	 * 如果无特殊逻辑需要处理, confirm也可以省略
	 */
	@CompensableConfirm
	@Transactional
	public void confirmTransfer(String sourceAcctId, String targetAcctId, double amount) {
		System.out.printf("done decrease: acct= %s, amount= %7.2f%n", targetAcctId, amount);
	}

}
