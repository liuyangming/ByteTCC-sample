package com.bytesvc.consumer.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TransferDao {

	@Update("update tb_account_two set amount = amount + #{amount} where acct_id = #{acctId}")
	public int increaseAmount(@Param("acctId") String accountId, @Param("amount") double amount);

	@Update("update tb_account_two set amount = amount - #{amount} where acct_id = #{acctId}")
	public int cancelIncrease(@Param("acctId") String accountId, @Param("amount") double amount);

}
