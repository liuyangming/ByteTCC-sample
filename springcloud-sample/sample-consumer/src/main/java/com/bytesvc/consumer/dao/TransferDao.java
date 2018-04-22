package com.bytesvc.consumer.dao;

import org.apache.ibatis.annotations.Param;

public interface TransferDao {

	public int increaseAmount(@Param("acctId") String accountId, @Param("amount") double amount);

	public Double getAcctAmount(@Param("acctId") String accountId);

	public int confirmIncrease(@Param("acctId") String accountId, @Param("amount") double amount);

	public int cancelIncrease(@Param("acctId") String accountId, @Param("amount") double amount);

}
