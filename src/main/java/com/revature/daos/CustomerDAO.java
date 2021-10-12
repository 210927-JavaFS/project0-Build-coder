package com.revature.daos;

import java.util.List;
import com.revature.models.Customer;

public interface CustomerDAO {

	// class example
	public List<Customer> findAll(); 
	
	// boolean to return true/false if it pass/failed
	public boolean addProfile(Customer c);

	// boolean to return true/false if it pass/failed
	public boolean removeProfile(Customer c);

	public Customer findByID(String customer_id);
}
