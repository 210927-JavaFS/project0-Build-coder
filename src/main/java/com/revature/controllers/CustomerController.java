package com.revature.controllers;

import java.util.Scanner;
import java.util.UUID;

import com.revature.models.Account;
import com.revature.services.AccountService;

// temporarily using Array's in this class
import java.util.ArrayList;
	
/**
 * Customers should be able to register with a user name and password, 
 * and appy to open an account
 * 
 * Once the account has been approved, customers should be able to 
 * withdraw/deposit/transfer funds between their accounts
 */
public class CustomerController extends MenuController {

	private static AccountService accountService = new AccountService();
	private static boolean running = true;
	private static ArrayList<Account> customerAccounts = new ArrayList<>();

	Scanner scan = createScanner();

    @Override
	public void mainMenu(String name, ArrayList<Account>bankAccounts){
		do { // customer functionality
			System.out.println();
			System.out.println("Please choose an option: ");		
			System.out.println("1: Create a bank account");
			System.out.println("2: Deposit");
			System.out.println("3: Withdraw");
			System.out.println("4: Transfer");
			System.out.println("5: View Accounts");
			System.out.println("Enter any key that's not 1-4 to quit");
			System.out.println();
			
			int response = scan.nextInt();
			
			switch (response) {
				case 1:
					Account account = null;
					account = buildBankAccount(account, name);
					accountService.addToList(account, bankAccounts);
                    System.out.println("Congrats " + name + "! You opened a new bank account");
					buildCustomerAccounts(bankAccounts, name);
					break;
				case 2:
					deposit(customerAccounts);
					break;
				case 3:
					withdraw(customerAccounts);
					break;
				case 4:
					transfer(customerAccounts);
					break;
				case 5:
					viewAccounts(customerAccounts);
					break;
		
				default:
					System.out.println("Exited customer interface");
					running = false;
			}	
		} while (running);

		running = true;
	}
	
	public Account buildBankAccount(Account account, String name) {

		String accountID = UUID.randomUUID().toString();
		int balance = 0;
		account=accountService.createAccount(name,accountID,balance);

		return account;
	}

	public void buildCustomerAccounts(ArrayList<Account> bankAccounts, String name) {

		for (Account account : bankAccounts) {
			String account_name = account.getName();
			if (name == account_name) {
				customerAccounts.add(account);
			}
		}
	}
	
	public void deposit(ArrayList<Account> customerAccounts){
		System.out.println("Which account would you like to deposit money in?");
		viewAccounts(customerAccounts);
		int i = scan.nextInt();
		System.out.println("How much would you like to deposit?");
		int amount = scan.nextInt();
		accountService.add(customerAccounts.get(i), amount);
	}

	public void withdraw(ArrayList<Account> customerAccounts){
		System.out.println("Which account do you want to withdraw from?");
		viewAccounts(customerAccounts);
		int i = scan.nextInt();
		System.out.println("How much would you like to withdraw?");
		int amount = scan.nextInt();
		accountService.subtract(customerAccounts.get(i), amount);
	}

	public void transfer(ArrayList<Account> customerAccounts){
		System.out.println("Which account do you want to transfer money from?");
		viewAccounts(customerAccounts);
		int i = scan.nextInt();
		System.out.println("Which account would you like to transfer the money to?");
		viewAccounts(customerAccounts);
		int j = scan.nextInt();
		System.out.println("How much would you like to transfer?");
		int amount = scan.nextInt();
		accountService.subtract(customerAccounts.get(i), amount);
		accountService.add(customerAccounts.get(j), amount);
	}

	@Override
	public void viewAccounts(ArrayList<Account> customerAccounts) {
		int count = 0;

		for (Account account : customerAccounts) {
			System.out.print(count + ": ");
			System.out.print(account);
			System.out.println(": $" + account.getBalance());
			count++;
		}
	}
}
