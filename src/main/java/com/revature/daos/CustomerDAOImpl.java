package com.revature.daos;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Customer;
import com.revature.utils.ConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO{
	
	private static Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);

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
						result.getString("customer_password")
						);
			
				list.add(customer);
			}
			
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addProfile(Customer x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeProfile(Customer x) {
		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "DELETE FROM customers WHERE customer_id = ?";
				
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setObject(1, x.getId());
		
			statement.execute();
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Customer findByID(String customer_id) {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM accounts WHERE account_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, customer_id);
			
			ResultSet result = statement.executeQuery();
			
			Customer customer = new Customer();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			if(result.next()) {
				
				customer.setId(result.getString("customer_id"));
				customer.setName(result.getString("customer_name"));
				customer.setPassword(result.getString("customer_password"));
			}
			
			return customer;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
