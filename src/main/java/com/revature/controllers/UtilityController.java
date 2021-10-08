package com.revature.controllers;

import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;

import com.revature.services.*;

// temporarily using models in this class
import com.revature.models.*;

public abstract class UtilityController {

	private static CustomerService customerService = new CustomerService();
	private static EmployeeService employeeService = new EmployeeService();
	private static AccountService accountService = new AccountService();

	private static boolean running = true;
	Scanner scan = createScanner();

	public String customerLogin(ArrayList<Customer>profiles){
		do {
			String name, password, id;

			System.out.println("Please enter your name: ");
			name = scan.nextLine();
			System.out.println("Please enter your password: ");
			password = scan.nextLine();
			id = UUID.randomUUID().toString();

			if (!(name.isEmpty() || password.isEmpty())) {
				Customer profile = customerService.createAccount(name,password,id);
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

	public String employeeLogin(ArrayList<Employee>profiles){
		do {
			String name, password, id;

			System.out.println("Please enter your name: ");
			name = scan.nextLine();
			System.out.println("Please enter your password: ");
			password = scan.nextLine();
			id = UUID.randomUUID().toString();

			if (!(name.isEmpty() || password.isEmpty())) {
				Employee profile = employeeService.createAccount(name,password,id);
				employeeService.addToList(profile, profiles);
				employeeService.save(profile);
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

	public void viewAccounts(ArrayList<Account> bankAccounts){
		int count = 0;

		for (Account account : bankAccounts) {
			System.out.print(count + ": ");
			System.out.print(account);
			System.out.println(": $" + account.getBalance());
			count++;
		}
	}

	public void viewCustomerProfiles(ArrayList<Customer> profiles){
		int count = 0;

		for (Customer profile : profiles) {
			System.out.print(count + ": ");
			System.out.print(profile);
			System.out.println(": " + profile.getName());
			count++;
		}
	}

	public void viewEmployeeProfiles(ArrayList<Employee> profiles){
		int count = 0;

		for (Employee profile : profiles) {
			System.out.print(count + ": ");
			System.out.print(profile);
			System.out.println(": " + profile.getName());
			count++;
		}
	}

	public boolean approveAccount(Account x){
		boolean approve;
		scan = createScanner();
		System.out.println("Do you approve the creation of " + x + " account?");
		System.out.println("Type 'yes' or 'no");
		String response = scan.nextLine();
		
		if (response == "yes") {
			approve = true;
		} else{
			approve = false;
		}

		return approve;
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

	public String getUserName(ArrayList<Account> bankAccounts, String userID){
		for (Account account : bankAccounts) {
			if (account.getAccountID() == userID) {
				return account.getName();
			}
		}

		return "";
	}
	
	/**
	 * Helper method to create a Scanner object
	 * @return ScannerObject
	 */
	public Scanner createScanner(){
		Scanner scan = new Scanner(System.in);
		
		return scan;
	}

	public abstract void menu(String userID, ArrayList<Account>bankAccounts, ArrayList<Customer>profiles);
}
