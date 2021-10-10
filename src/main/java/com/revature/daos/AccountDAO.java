package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {

	public List<Account> findAll(); 
	public Account findAccount(String id);
	
	// boolean to return true/false if it pass/failed
	public boolean addAccount(Account x);

	// boolean to return true/false if it pass/failed
	public boolean removeAccount(Account x);
	public Account findByID(String account_id);
}
