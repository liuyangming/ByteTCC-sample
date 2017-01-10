package com.bytesvc.svc4jpa.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bytesvc.svc4jpa.model.Account;

@Component("accountDao")
public class AccountDaoImpl implements IAccountDao {
	@Autowired
	private SessionFactory sessionFactory;

	private void closeSessionIfNecessary(Session session) {
		if (session != null) {
			session.close();
		}
	}

	public Account findById(String identifier) {
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			return (Account) session.get(Account.class, identifier);
		} finally {
			this.closeSessionIfNecessary(session);
		}
	}

	public void insert(Account account) {
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			session.save(account);
			session.flush();
		} finally {
			this.closeSessionIfNecessary(session);
		}
	}

	public void update(Account account) {
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			session.saveOrUpdate(account);
			session.flush();
		} finally {
			this.closeSessionIfNecessary(session);
		}
	}

	public void delete(Account account) {
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			session.delete(account);
			session.flush();
		} finally {
			this.closeSessionIfNecessary(session);
		}
	}

}
