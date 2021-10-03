package com.revature.services;

import java.util.ArrayList;
import com.revature.models.Account;

public class AccountService {

	private ArrayList<Account> allAccounts = new ArrayList<>();

	public Account createBankAccount(String name, String accountID, int balance) {
		return new Account(name, accountID, balance);
	}

	public Account createAccount(String name, String accountID, int balance) {
		return new Account(name, accountID, balance);
	}

	public void addDeposit(Account account, int amount) {
		account.setBalance(account.getBalance() + amount);
	}

    public void addToList(Account x){
		allAccounts.add(x);
    }
}
