package com.bytesvc.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytesvc.svc4jpa.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, String> {

}
