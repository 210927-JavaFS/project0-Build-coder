package com.revature.services;

import java.util.List;

import com.revature.daos.AccountDAOImpl;
import com.revature.models.*;

public class AccountService {

	private static AccountDAOImpl accountDAO = new AccountDAOImpl();

	public void createAccount(String id, float balance, boolean active, Customer c){
		Account a = new Account(id, balance, active, c);

		accountDAO.addAccount(a);
	}

	public void add(Account a, float amount) {
		a.setBalance(a.getBalance() + amount);
		accountDAO.updateBalance(a);
	}

	public void subtract(Account a, float amount) {
		a.setBalance(a.getBalance() - amount);
		accountDAO.updateBalance(a);
	}

	// class example
	public List<Account> getAllAccounts(){
		return accountDAO.findAll();
	}

	public void remove(Account a){
		accountDAO.removeAccount(a);
	}

	public void updateAccountStatus(Account a){
		a.setActive(true);
		accountDAO.updateActive(a);
	}
}
