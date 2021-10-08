package com.revature.services;

import java.util.ArrayList;

import com.revature.daos.AccountDAO;
import com.revature.models.Account;

public class AccountService {

	private ArrayList<Account> allAccounts = new ArrayList<>();
	private static AccountDAO accountDAO = new AccountDAO();

	public Account createAccount(String name, String accountID, int balance) {
		return new Account(name, accountID, balance);
	}

	public void add(Account account, int amount) {
		account.setBalance(account.getBalance() + amount);
	}

	public void subtract(Account account, int amount) {
		account.setBalance(account.getBalance() - amount);
	}

    public void addToList(Account x, ArrayList<Account>bankAccounts){
		// allAccounts.add(x);
		bankAccounts.add(x);
    }

	public void save(Account account) {
		accountDAO.writeAccount(account);
	}
}
