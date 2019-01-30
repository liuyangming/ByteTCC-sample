package com.bytesvc.provider.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytesvc.provider.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, String> {
}
