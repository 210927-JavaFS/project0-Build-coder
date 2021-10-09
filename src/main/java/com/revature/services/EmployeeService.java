package com.revature.services;

import java.util.ArrayList;

import com.revature.daos.EmployeeDAOImpl;
import com.revature.models.Employee;

public class EmployeeService {

	private ArrayList<Employee> allAccounts = new ArrayList<>();
	private static EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

	public Employee createAccount(String name, String password, String id) {
		return new Employee(name, password, id);
	}

    public void addToList(Employee x, ArrayList<Employee>profiles){
		// allAccounts.add(x);
		profiles.add(x);
    }

	public void save(Employee profile) {
		employeeDAO.writeProfile(profile);
	}
}
