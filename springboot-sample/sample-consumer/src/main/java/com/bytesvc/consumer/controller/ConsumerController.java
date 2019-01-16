package com.bytesvc.consumer.controller;

import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bytesvc.consumer.interfaces.ITransferService;

@Compensable(interfaceClass = ITransferService.class, cancellableKey = "transferServiceCancel")
@RestController
public class ConsumerController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RestTemplate restTemplate;

	@ResponseBody
	@RequestMapping(value = "/transfer/{source}/{target}/{amount}", method = RequestMethod.POST)
	@Transactional
	public void transferAmount(@PathVariable("source") String source, @PathVariable("target") String target,
			@PathVariable("amount") double amount) {
		this.restTemplate.postForEntity(String.format("http://127.0.0.1:3051/decrease/%s/%s", source, amount), null, Void.TYPE);
		this.jdbcTemplate.update("update tb_account_two set amount = amount + ? where acct_id = ?", amount, target);
		System.out.printf("exec transfer: source= %s, target= %s, amount= %7.2f%n", source, target, amount);
	}

}
