package com.revature.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.*;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class AccountDAOImpl implements AccountDAO{
	
	private static Logger log = LoggerFactory.getLogger(AccountDAOImpl.class);
	private CustomerDAO customerDAO = new CustomerDAOImpl();

	@Override
	public List<Account> findAll() {

		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM accounts;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Account> list = new ArrayList<>();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			while(result.next()) {
				Account account = new Account(
						result.getString("account_id"), 
						result.getInt("account_balance"),
						result.getBoolean("account_active"),
						null
						);

				String customer_id = result.getString("customer_id");

				if(customer_id!=null) {
					Customer customer = customerDAO.findByID(customer_id);
					account.setCustomer(customer);
				}
			
				list.add(account);
			}
			
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO accounts (account_id, account_balance, account_active) "
					+ "VALUES (?,?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++count, account.getAccountID());
			statement.setFloat(++count, account.getBalance());
			statement.setBoolean(++count, account.isActive());
			
			statement.execute();
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account findAccount(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAccount(Account x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account findByID(String account_id) {
 
		return null;
	}

	@Override
	public boolean updateBalance(Account x){

		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "UPDATE accounts SET account_balance = " + "?"
			+ " WHERE account_id = " + "?";
	
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setFloat(++count, x.getBalance());
			statement.setString(++count, x.getAccountID());
			
			statement.execute();
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
