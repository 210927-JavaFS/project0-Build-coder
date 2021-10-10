package com.revature.daos;

import java.util.List;
import com.revature.models.Customer;

public interface CustomerDAO {

	// class example
	public List<Customer> findAll(); 

	public Customer findProfile(String id);
	
	// boolean to return true/false if it pass/failed
	public boolean addProfile(Customer x);

	// boolean to return true/false if it pass/failed
	public boolean removeProfile(Customer x);

	public Customer findByID(String customer_id);

	public void setAccount(Customer x);
}
