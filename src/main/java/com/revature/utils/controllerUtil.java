package com.revature.utils;

import java.util.Scanner;
import java.util.UUID;
import java.util.List;

import com.revature.services.*;

// temporarily using models in this class
import com.revature.models.*;


public abstract class ControllerUtil {

	private static CustomerService customerService = new CustomerService();
	private static AccountService accountService = new AccountService();
	private static EncryptionUtil encryptionUtil = new EncryptionUtil();

	private static boolean running = true;
	Scanner scan = createScanner();

	public String createProfile() throws Exception{
		System.out.println("Create new profile:");
		do {
			String name, password, id, encryptPass;

			System.out.println("Please enter your name: ");
			name = scan.nextLine();
			System.out.println("Please enter a password: ");
			password = scan.nextLine();
			id = UUID.randomUUID().toString();
			encryptPass = encryptionUtil.encrypt(password);

			if (!(name.isEmpty() || password.isEmpty())) {
				customerService.createAccount(id,name,password,encryptPass);
				System.out.println();
				System.out.println("Congrats " + name + " you have created a user profile");
				running = false;
				return id;
			} else {
				System.out.println();
				System.out.println("Name and/or password is incomplete. Try again");
				System.out.println();
			}
		} while(running);

		running = true;
		// won't ever return this empty String. 
		// just need to have it to satisfy 
		// compiler cause method returns a String: name
		return "";
	}

	public String logIn(){
		String name, password;

		System.out.println("Log in to profile:");
		System.out.println("Please enter your name: ");
		name = scan.nextLine();
		System.out.println("Please enter your password: ");
		password = scan.nextLine();

		try {

			String id = findByPassword(name, password);

			if(findByPassword(name, password) != ""){
				System.out.println("Welcome back " + name);
				return id;
			} else {
				System.out.println("We could not find your record in our database");
				System.out.println("You need to create a new profile");
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public String findByPassword(String name, String password){

		List<Customer> list = customerService.getAllProfiles();
		for(Customer c:list) {
			password = encryptionUtil.decrypt(password);
			if ((c.getName().equals(name)) && (c.getPassword().equals(password))) {
				return c.getId();
			}
		}
		return "";
	}

	public Account findByAccountID(String accountID){
		List<Account> list = accountService.getAllAccounts();
		for(Account x:list) {
			if (accountID.equals(x.getAccountID())) {
				return x;
			}
		}

		System.out.println("Could not find Account");
		return null;
	}

	public Customer findByCustomerID(String customerID){
		List<Customer> list = customerService.getAllProfiles();
		for(Customer x:list) {
			if (customerID.equals(x.getId())) {
				return x;
			}
		}

		System.out.println("Could not find Customer");
		return null;
	}

	public void deposit(){
		scan = createScanner();
		System.out.println("Which account would you like to deposit money in?");
		viewAccounts();
		System.out.println("Please enter the Account's ID: ");
		String accountID = scan.nextLine();
		Account account = findByAccountID(accountID);
		System.out.println("How much would you like to deposit?");
		float amount = scan.nextFloat();
		accountService.add(account, amount);
	}

	public void withdraw(){
		scan = createScanner();
		System.out.println("Which account would you like to withdraw money from?");
		viewAccounts();
		System.out.println("Please enter the Account's ID: ");
		String accountID = scan.nextLine();
		Account account = findByAccountID(accountID);
		System.out.println("How much would you like to withdraw?");
		float amount = scan.nextFloat();
		accountService.subtract(account, amount);
	}

	public void transfer(){
		scan = createScanner();
		System.out.println("Which account do you want to transfer money from?");
		viewAccounts();
		System.out.println("Please enter the Account's ID: ");
		String fromAccountID = scan.nextLine();
		Account fromAccount = findByAccountID(fromAccountID);
		System.out.println("Which account would you like to transfer the money to?");
		String toAccountID = scan.nextLine();
		Account toAccount = findByAccountID(toAccountID);
		System.out.println("How much would you like to transfer?");
		float amount = scan.nextFloat();
		accountService.subtract(fromAccount, amount);
		accountService.add(toAccount, amount);
	}

	// class example
	public void viewProfiles(){
		System.out.println("Here are all the Customer Profiles:");
		List<Customer> list = customerService.getAllProfiles();
		for(Customer x:list) {
			System.out.println(x);
		}
	}

	public void viewAccounts(){
		System.out.println("Here are all the Accounts:");
		List<Account> list = accountService.getAllAccounts();
		for(Account x:list) {
			System.out.println(x);
		}
	}

	public void activateAccount(){
		scan = createScanner();
		System.out.println("Which account would you like to activate?");
		viewAccounts();
		String accountID = scan.nextLine();
		Account account = findByAccountID(accountID);
		boolean active = account.isActive();

		if (!active) {
			scan = createScanner();
			System.out.println("Do you want to activate account: " + account + "?");
			System.out.println("Type 'yes' or 'no");
			String response = scan.nextLine();
			
			if (response == "yes") {
				account.setActive(true);
				System.out.println("Account: " + account + " is now active");
			} else{
				System.out.println("Account: " + account + " remains inactive");
			}
		} else{
			System.out.println("Account is active");
		}
	}
	
	/**
	 * Helper method to create a Scanner object
	 * @return ScannerObject
	 */
	public Scanner createScanner(){
		Scanner scan = new Scanner(System.in);
		
		return scan;
	}

	public void cancelAccount(){
		scan = createScanner();
		System.out.println("Which account would you like to cancel?");
		viewAccounts();
		System.out.println("Please enter the Account's ID: ");
		String accountID = scan.nextLine();
		Account account = findByAccountID(accountID);
		accountService.remove(account);
	}

	public void cancelProfile(){
		scan = createScanner();
		System.out.println("Which account would you like to cancel?");
		viewProfiles();
		System.out.println("Please enter the Customer's ID: ");
		String customerID = scan.nextLine();
		Customer customer = findByCustomerID(customerID);
		customerService.remove(customer);
	}

	public abstract void menu();
}
