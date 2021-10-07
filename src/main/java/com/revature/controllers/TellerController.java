package com.revature.controllers;

import java.util.ArrayList;

import com.revature.models.Account;


/**
 * Employees of the bank should be able to view the
 * customer's login account and banking account(s)
 *
 * Employees of the bank should be able to approve/deny 
 * open applications for accounts. 
 */
public class TellerController extends MenuController{

	@Override
	public void mainMenu(String name, ArrayList<Account> bankAccounts, ArrayList<Account> customerAccounts) {
		
	}

	@Override
	public void viewAccounts(ArrayList<Account> bankAccounts) {
		int count = 0;

		for (Account account : bankAccounts) {
			System.out.print(count + ": ");
			System.out.print(account);
			System.out.println(": $" + account.getBalance());
			count++;
		}
	}	
}
