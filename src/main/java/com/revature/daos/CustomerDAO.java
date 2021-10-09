package com.revature.daos;

import java.util.List;
import com.revature.models.Customer;

public interface CustomerDAO {
    public List<Customer> findAll(); 
	public Customer findAccount(int id);
	public boolean addAccount(Customer account);
}
