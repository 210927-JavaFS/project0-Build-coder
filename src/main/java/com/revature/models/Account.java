package com.revature.models;

import java.util.HashMap;


public class Account {
    private String name;
	private String accountID;
	private int balance;
	private HashMap<String, String> map = new HashMap<String, String>();

	public Account(String name, String accountID, int balance) {
		this.name = name;
		this.accountID = accountID;
		this.balance = balance;
		this.map.put(name, accountID);
	}
	
	Account(){
		super();
	}

	public HashMap<String, String> getMap(String string) {
		return map;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
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

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}