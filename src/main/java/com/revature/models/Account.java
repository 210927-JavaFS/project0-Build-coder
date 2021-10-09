package com.revature.models;

public class Account {
    private String name;
	private String accountID;
	private int balance;
	private boolean active;

	public Account(String name, String accountID, int balance, boolean active) {
		this.name = name;
		this.accountID = accountID;
		this.balance = balance;
		this.active = active;
	}
	
	Account(){
		super();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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