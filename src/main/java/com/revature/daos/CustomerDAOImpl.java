package com.revature.daos;

import java.sql.Connection;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.utils.ConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO{
	
	private static Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);
	private AccountDAO accountDAO = new AccountDAOImpl();

	// class example
	@Override
	public List<Customer> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM customers;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Customer> list = new ArrayList<>();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			while(result.next()) {
				Customer customer = new Customer(
						result.getString("customer_id"), 
						result.getString("customer_name"),
						result.getString("customer_password"),
						null // account object
						);
				String account_id = result.getString("account_id");

				if(account_id!=null) {
					Account account = accountDAO.findByID(account_id);
					customer.setAccount(account);
				}
				
				list.add(customer);
			}
			
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Customer findProfile(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean addProfile(Customer x) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeProfile(Customer x) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Customer findByName(String customer_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAccount(Customer x){

	}
}
