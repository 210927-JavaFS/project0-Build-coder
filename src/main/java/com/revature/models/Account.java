package com.revature.models;

import java.util.Objects;

public class Account {
	private String accountID;
	private float balance;
	private boolean active;
	private Customer customer;

	public Account(String accountID, float balance, boolean active, Customer customer) {
		super();
		this.accountID = accountID;
		this.balance = balance;
		this.active = active;
		this.customer = customer;
	}

	public Account(){
		super();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountID, active, balance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountID, other.accountID) && active == other.active
				&& Float.floatToIntBits(balance) == Float.floatToIntBits(other.balance);
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", active=" + active + ", balance=" + balance + "]";
	}
}