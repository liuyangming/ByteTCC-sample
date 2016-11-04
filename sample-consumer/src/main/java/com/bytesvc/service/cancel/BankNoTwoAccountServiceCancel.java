package com.bytesvc.service.cancel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.service.IAccountService;

@Service("accountServiceCancel")
public class BankNoTwoAccountServiceCancel implements IAccountService {

	@SuppressWarnings("restriction")
	@javax.annotation.Resource(name = "dataSource")
	private DataSource dataSource;

	@Transactional(rollbackFor = ServiceException.class)
	public void increaseAmount(String accountId, double amount) throws ServiceException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.dataSource.getConnection();
			stmt = conn.prepareStatement("update tb_account_two set amount = amount - ? where acct_id = ?");
			stmt.setDouble(1, amount);
			stmt.setString(2, accountId);
			int value = stmt.executeUpdate();
			if (value != 1) {
				throw new ServiceException("ERROR!");
			}
			System.out.printf("undo increase: acct= %s, amount= %7.2f%n", accountId, amount);
		} catch (SQLException ex) {
			throw new ServiceException(ex.getMessage());
		} finally {
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void decreaseAmount(String accountId, double amount) throws ServiceException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.dataSource.getConnection();
			stmt = conn.prepareStatement("update tb_account_two set amount = amount + ? where acct_id = ?");
			stmt.setDouble(1, amount);
			stmt.setString(2, accountId);
			int value = stmt.executeUpdate();
			if (value != 1) {
				throw new ServiceException("ERROR!");
			}
			System.out.printf("undo decrease: acct= %s, amount= %7.2f%n", accountId, amount);
		} catch (SQLException ex) {
			throw new ServiceException(ex.getMessage());
		} finally {
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}

}
