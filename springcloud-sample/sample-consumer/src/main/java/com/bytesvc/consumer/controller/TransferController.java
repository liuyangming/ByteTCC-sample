package com.bytesvc.consumer.controller;

import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bytesvc.consumer.service.ITransferService;
import com.bytesvc.feign.service.IAccountService;

@Compensable(interfaceClass = ITransferService.class, confirmableKey = "transferServiceConfirm", cancellableKey = "transferServiceCancel")
@RestController
public class TransferController implements ITransferService {
	// @Autowired
	// private RestTemplate restTemplate;

	@Autowired
	private IAccountService acctService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	@Transactional
	public void transfer(@RequestParam String sourceAcctId, @RequestParam String targetAcctId, @RequestParam double amount) {
		this.increaseAmount(targetAcctId, amount);
		this.acctService.decreaseAmount(sourceAcctId, amount);
	}

	// @RequestMapping(value = "/transfer", method = RequestMethod.POST)
	// @Transactional
	// public void transfer(@RequestParam String sourceAcctId, @RequestParam String targetAcctId, @RequestParam double amount) {
	// int statusCode = 0;
	// try {
	// HttpEntity<Object> request = new HttpEntity<Object>(null);
	// ResponseEntity<Object> response = restTemplate.postForEntity(
	// "http://SPRINGCLOUD-SAMPLE-PROVIDER/decreaseAmount?a={v1}&b={v2}", request, Object.class, sourceAcctId,
	// amount);
	// statusCode = response.getStatusCodeValue();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// throw new IllegalStateException("ERROR!");
	// }
	//
	// if (statusCode < 200 || statusCode >= 300) {
	// throw new IllegalStateException("ERROR!");
	// }
	//
	// this.increaseAmount(targetAcctId, amount);
	// }

	private void increaseAmount(String acctId, double amount) {
		int value = this.jdbcTemplate.update("update tb_account_two set frozen = frozen + ? where acct_id = ?", amount, acctId);
		if (value != 1) {
			throw new IllegalStateException("ERROR!");
		}
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
