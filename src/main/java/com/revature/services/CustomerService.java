package com.revature.services;

import java.util.ArrayList;
import com.revature.models.Customer;

public class CustomerService {

	private ArrayList<Customer> allAccounts = new ArrayList<>();

	public Customer createAccount(String name, String password, String id) {
		return new Customer(name, password, id);
	}

    public void addToList(Customer x, ArrayList<Customer>profiles){
		// allAccounts.add(x);
		profiles.add(x);
    }
}
