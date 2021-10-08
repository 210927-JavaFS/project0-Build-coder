package com.revature.services;

import java.util.ArrayList;
import com.revature.models.Employee;

public class EmployeeService {

	private ArrayList<Employee> allAccounts = new ArrayList<>();

	public Employee createAccount(String name, String password, String id) {
		return new Employee(name, password, id);
	}

    public void addToList(Employee x, ArrayList<Employee>profiles){
		// allAccounts.add(x);
		profiles.add(x);
    }
}
