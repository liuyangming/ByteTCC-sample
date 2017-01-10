package com.bytesvc.svc4jpa.dao;

import com.bytesvc.svc4jpa.model.Account;

public interface IAccountDao {

	public Account findById(String identifier);

	public void insert(Account account);

	public void update(Account account);

	public void delete(Account account);

}
