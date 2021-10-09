package com.revature.daos;

import java.util.List;
import com.revature.models.Employee;

public interface EmployeeDAO {
    public List<Employee> findAll(); 
	public Employee findAccount(int id);
	public boolean addAccount(Employee account);
}
