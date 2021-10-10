package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;

public class AccountService {

	private ArrayList<Account> allAccounts = new ArrayList<>();
	private static AccountDAOImpl accountDAO = new AccountDAOImpl();

	public Account createAccount(String name, String accountID, int balance, boolean active) {
		return new Account(name, accountID, balance, active);
	}

	public void add(Account x, float amount) {
		x.setBalance(x.getBalance() + amount);
		accountDAO.updateBalance(x);
	}

	public void subtract(Account x, float amount) {
		x.setBalance(x.getBalance() - amount);
		accountDAO.updateBalance(x);
	}

    public void addToList(Account x, ArrayList<Account>bankAccounts){
		// allAccounts.add(x);
		bankAccounts.add(x);
    }

	public void updateAccount(Account x){
		// if account == null, init account
		
	}

	// class example
	public List<Account> getAllAccounts(){
		return accountDAO.findAll();
	}

	public void remove(Account x){
		accountDAO.removeAccount(x);
	}

	public void save(Account account) {
		accountDAO.writeAccount(account);
	}
}
