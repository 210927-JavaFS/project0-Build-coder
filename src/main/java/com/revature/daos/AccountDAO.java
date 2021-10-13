package com.revature.daos;

import java.util.List;

import com.revature.models.*;

public interface AccountDAO {

	// class example
	public List<Account> findAll(); 
	
	// boolean to return true/false if it pass/failed
	public boolean addAccount(Account a);

	// boolean to return true/false if it pass/failed
	public boolean removeAccount(Account a);

	public Account findByID(String account_id);

	// boolean to return true/false if it pass/failed
	public boolean updateBalance(Account a);

	// boolean to return true/false if it pass/failed
	public boolean updateActive(Account a);
}
