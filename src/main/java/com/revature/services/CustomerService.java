package com.revature.services;

import com.revature.daos.CustomerDAO;
import com.revature.models.CustomerAccount;

public class CustomerService {
	
	private static CustomerDAO customerDAO = new CustomerDAO();
	
	public CustomerAccount createAccount(String name, String accountID, int balance) {
			
//		CustomerAccount account = new CustomerAccount(name, accountID, balance);
		return new CustomerAccount(name, accountID, balance);
	}
}
