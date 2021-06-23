package com.bytesvc.consumer.controller;

import org.bytesoft.compensable.Compensable;
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

/**
 * 本类中不涉及Confirm逻辑, Confirm逻辑样例在SimplifiedController类中.<br />
 * 如需为本类添加Confirm逻辑, 可参考Cancel逻辑的做法, 设置confirmableKey即可.
 */
@Compensable(interfaceClass = ITransferService.class, cancellableKey = "transferServiceCancel")
@RestController
public class TransferController implements ITransferService {
	@Autowired
	private TransferDao transferDao;

	@Autowired
	private IAccountService acctService;

	@ResponseBody
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	@Transactional
	public void transfer(@RequestParam String sourceAcctId, @RequestParam String targetAcctId, @RequestParam double amount) {
		this.acctService.decreaseAmount(sourceAcctId, amount);
		this.increaseAmount(targetAcctId, amount);
	}

	private void increaseAmount(String acctId, double amount) {
		this.transferDao.increaseAmount(acctId, amount);
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
