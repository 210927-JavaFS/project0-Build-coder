package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {
    	
	public List<Account> findAll(); 
	public Account findAccount(int id);
	public boolean addAccount(Account account);
}
