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
			System.out.println("Enter any key that's not 1-4 to quit");
			System.out.println();
			
			int response = scan.nextInt();
			
			switch (response) {
				case 1:
					// Scanner scan = createScanner();
					// System.out.println("What is your name?");
					// String name = scan.nextLine();
					Account account = null;
					account = buildBankAccount(account, name);
					accountService.addToList(account, bankAccounts);
                    System.out.println("Congrats " + name + "! You opened a new bank account");
					break;
				case 2:
					System.out.println("Which account would you like to deposit money in?");
					viewAccounts(bankAccounts);
					Scanner scan = createScanner();
					int accountNum = scan.nextInt();
					// find customer account
					// deposit(account);
					// break;
		//		case 3:
		//			withdraw();
		//			break;
		//		case 4:
		//			transfer();
		//			break;
		
				default:
					System.out.println("Exited customer interface");
					running = false;
			}	
		} while (running);
	}
	
	public Account buildBankAccount(Account account, String name) {
		String accountID = UUID.randomUUID().toString();
		int balance = 0;
		account=accountService.createAccount(name,accountID,balance);

		return account;
	}

	public void deposit(Account account) {
		scan = createScanner();

		System.out.println("How much do you want to deposit?");
		int amount = scan.nextInt();
		accountService.addDeposit(account, amount);
	}

	@Override
	public void viewAccounts(ArrayList<Account> bankAccounts) {
		int count = 0;

		for (Account account : bankAccounts) {
			System.out.print(count + ": ");
			System.out.print(account);
			System.out.println();
			count++;
		}
	}


}
