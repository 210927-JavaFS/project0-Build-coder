package com.revature.services;

import java.util.List;

import com.revature.daos.CustomerDAOImpl;
import com.revature.models.Customer;

public class CustomerService {

	private static CustomerDAOImpl customerDAO = new CustomerDAOImpl();


	public void createAccount(String id, String name, String password, String encryptPass){
		Customer c = new Customer(id, name, password);

		// only write encrypted password to db
		c.setPassword(encryptPass);
		customerDAO.addProfile(c);
	}

	// class example
	public List<Customer> getAllProfiles(){
		return customerDAO.findAll();
	}

	public void remove(Customer c){
		customerDAO.removeProfile(c);
	}
}
