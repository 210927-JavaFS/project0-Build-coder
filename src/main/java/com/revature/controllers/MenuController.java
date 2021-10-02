package com.revature.controllers;

import java.util.Scanner;
import java.util.UUID;

import com.revature.models.CustomerAccount;
import com.revature.services.CustomerService;

public class MenuController {
	
	private static Scanner scan = new Scanner(System.in);
	private static CustomerService customerService = new CustomerService();

	public CustomerAccount getAccount() {
		System.out.println("Please choose an option: ");		
		System.out.println("1: Apply to create an account");
		System.out.println("2: Deposit");
		System.out.println("3: Withdraw");
		System.out.println("4: Transfer");
		
		int response = scan.nextInt();
		
		CustomerAccount account = null;
		
		switch (response) {
			case 1:
				account = buildAccount();
				break;
	//		case 2:
	//			deposit();
	//			break;
	//		case 3:
	//			withdraw();
	//			break;
	//		case 4:
	//			transfer();
	//			break;
	
			default:
				break;		
		}
		
		return account;
	}
	
	public CustomerAccount buildAccount() {
		CustomerAccount account = null;
		System.out.println("What is your name?");
		String name = scan.nextLine();
		String accountID = UUID.randomUUID().toString();
		int balance = 0;
		account=customerService.createAccount(name,accountID,balance);
		
		return account;
		
	}
	
}
