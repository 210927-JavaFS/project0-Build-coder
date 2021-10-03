package com.revature.services;

import java.util.ArrayList;
import com.revature.models.CustomerAccount;

public class CustomerService {

	private ArrayList<CustomerAccount> allAccounts = new ArrayList<>();

		
	public CustomerAccount createAccount(String name, String accountID, int balance) {
		return new CustomerAccount(name, accountID, balance);
	}

	public void addDeposit(CustomerAccount account, int amount) {
		account.setBalance(account.getBalance() + amount);
	}

    public void addToList(CustomerAccount account){
        allAccounts.add(account);
    }

}
