package com.revature.models;

public class CustomerAccount {
	
	private String name;
	private String accountID;
	private int balance;

	public CustomerAccount(String name, String accountID, int balance) {
		this.name = name;
		this.accountID = accountID;
		this.balance = balance;
	}
	
	CustomerAccount(){
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
