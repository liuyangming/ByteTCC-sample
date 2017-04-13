package com.bytesvc.jpa.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.bytesvc.dao.model.Account;

@Component("accountRepository")
public interface AccountRepository extends JpaRepository<Account, String> {

}
