package com.revature.utils;

import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import com.revature.services.*;

// temporarily using models in this class
import com.revature.models.Customer;
import com.revature.models.Account;

public abstract class ControllerUtil {

	private static CustomerService customerService = new CustomerService();
	private static AccountService accountService = new AccountService();

	private static boolean running = true;
	Scanner scan = createScanner();

	public String createProfile(){
		do {
			String name, password, id;

			System.out.println("Please enter your name: ");
			name = scan.nextLine();
			System.out.println("Please enter your password: ");
			password = scan.nextLine();
			id = UUID.randomUUID().toString();

			if (!(name.isEmpty() || password.isEmpty())) {
				Account emptyAccount = null;
				Customer profile = customerService.createAccount(name,password,id,emptyAccount);
				customerService.addToList(profile, profiles);
				customerService.save(profile);
				System.out.println();
				System.out.println("Congrats " + name + " you have created a user account");
				running = false;
				profile.setName(name);
				return profile.getId();
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

	public void logIn(){
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
		Scanner scan = createScanner();
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
		Scanner scan = createScanner();
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
		Scanner scan = createScanner();
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

	public void activateAccount(Account x){
		boolean active = x.isActive();

		if (!active) {
			scan = createScanner();
			System.out.println("Do you want to activate account: " + x + "?");
			System.out.println("Type 'yes' or 'no");
			String response = scan.nextLine();
			
			if (response == "yes") {
				x.setActive(true);
				System.out.println("Account: " + x + " is now active");
			} else{
				System.out.println("Account: " + x + " remains inactive");
			}
		} else{
			System.out.println("Account is active");
		}
	}

	public void removeDuplicates(ArrayList<Account> customerAccounts){
        // create a temp list
        ArrayList<Account> newList = new ArrayList<Account>();
  
        // traverse through temp list
        for (Account element : customerAccounts) {
  
            // if not a repeat, add it to temp list
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
  
		// remove list with repeats
		customerAccounts.clear();

		// rebuild customerAccounts with no repeats
		for (Account account : newList) {
			customerAccounts.add(account);
		}

		// clear temp list
		newList.clear();
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
		Scanner scan = createScanner();
		System.out.println("Which account would you like to cancel?");
		viewAccounts();
		System.out.println("Please enter the Account's ID: ");
		String accountID = scan.nextLine();
		Account account = findByAccountID(accountID);
		accountService.remove(account);
	}

	public void cancelProfile(){
		Scanner scan = createScanner();
		System.out.println("Which account would you like to cancel?");
		viewProfiles();
		System.out.println("Please enter the Customer's ID: ");
		String customerID = scan.nextLine();
		Customer customer = findByCustomerID(customerID);
		accountService.remove(customer);
	}

	public abstract void menu();
}
