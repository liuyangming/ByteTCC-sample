package com.bytesvc.provider.controller;

import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytesvc.provider.service.IAccountService;

/**
 * ByteTCC倾向于认为: 使用SpringCloud时, 直接对外提供服务的Controller应该明确规划好它是普通服务还是TCC服务.<br />
 * 因此, 0.4.x版本强制对外提供TCC服务的Controller必须加@Compensable注解(若没有实质业务, 也可以不必指定confirmableKey和cancellableKey).<br />
 * 若不加@Compensable注解, 则ByteTCC将其当成普通服务对待, 不接收Consumer端传播的事务上下文. 若它后续调用TCC服务, 则将开启新的TCC全局事务.
 */
@Compensable(interfaceClass = IAccountService.class, confirmableKey = "accountServiceConfirm", cancellableKey = "accountServiceCancel")
@RestController
public class AccountController implements IAccountService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ResponseBody
	@RequestMapping(value = "/increase", method = RequestMethod.POST)
	@Transactional
	public void increaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {
		int value = this.jdbcTemplate.update("update tb_account_one set frozen = frozen + ? where acct_id = ?", amount, acctId);
		if (value != 1) {
			throw new IllegalStateException("ERROR!");
		}
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@ResponseBody
	@RequestMapping(value = "/decrease", method = RequestMethod.POST)
	@Transactional
	public void decreaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {
		int value = this.jdbcTemplate.update(
				"update tb_account_one set amount = amount - ?, frozen = frozen + ? where acct_id = ?", amount, amount, acctId);
		if (value != 1) {
			throw new IllegalStateException("ERROR!");
		}
		System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);

		// throw new IllegalStateException("error");
	}

}
