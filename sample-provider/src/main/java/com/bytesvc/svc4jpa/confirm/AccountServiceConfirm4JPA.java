package com.bytesvc.svc4jpa.confirm;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.dao.IAccountDao;
import com.bytesvc.dao.model.Account;
import com.bytesvc.service.IAccountService;

@Service("accountServiceConfirm4JPA")
public class AccountServiceConfirm4JPA implements IAccountService {

	@SuppressWarnings("restriction")
	@javax.annotation.Resource(name = "accountDao")
	private IAccountDao accountDao;

	@Transactional(rollbackFor = ServiceException.class)
	public void increaseAmount(String acctId, double amount) throws ServiceException {
		Account account = this.accountDao.findById(acctId);
		account.setAmount(account.getAmount() + amount); // 真实业务中, 请考虑设置乐观锁/悲观锁, 以便并发操作时导致数据不一致
		account.setFrozen(account.getFrozen() - amount);
		this.accountDao.update(account);
		System.out.printf("[jpa] done increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void decreaseAmount(String acctId, double amount) throws ServiceException {
		Account account = this.accountDao.findById(acctId);
		account.setFrozen(account.getFrozen() - amount); // 真实业务中, 请考虑设置乐观锁/悲观锁, 以便并发操作时导致数据不一致
		this.accountDao.update(account);
		System.out.printf("[jpa] done decrease: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
