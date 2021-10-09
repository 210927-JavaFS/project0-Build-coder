package com.revature.services;

import java.util.ArrayList;

import com.revature.daos.CustomerDAOImpl;
import com.revature.models.Account;
import com.revature.models.Customer;

public class CustomerService {

	private ArrayList<Customer> allAccounts = new ArrayList<>();
	private static CustomerDAOImpl customerDAO = new CustomerDAOImpl();

	public Customer createAccount(String name, String password, String id, Account account) {
		return new Customer(name, password, id, account);
	}

    public void addToList(Customer x, ArrayList<Customer>profiles){
		// allAccounts.add(x);
		profiles.add(x);
    }

	public void save(Customer profile) {
		customerDAO.writeProfile(profile);
	}
}
