package com.revature.services;

import java.util.ArrayList;
import com.revature.models.Customer;

public class CustomerService {

	private ArrayList<Customer> allAccounts = new ArrayList<>();

	public Customer createAccount(String name, String password, String id) {
		return new Customer(name, password, id);
	}

    public void addToList(Customer x){
		allAccounts.add(x);
    }
}
