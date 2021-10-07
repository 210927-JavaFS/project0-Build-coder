package com.revature.daos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import com.revature.models.Customer;

public class CustomerDAO {
	
	// private static Logger log = LoggerFactory.getLogger(PlayerDAO.class);

	public void writeCustomer(Customer customer) {
		File customers = new File("//home//ec2-user//training//projects//project0//src//main//resources//customers.txt");
		
		try {
			if(customers.createNewFile()) {
				// log.info("Created new players file");
                System.out.println("Created new players file");
			}else {
				// log.info("players file already exists");
                System.out.println("players file already exists");
			}
		}catch(IOException e) {
			// log.error("Something went wrong trying to access players file: "+e.getMessage());
            System.out.println(e.getMessage());
		}
		
		try(FileWriter writer = new FileWriter("//home//ec2-user//training//projects//project0//src//main//resources//customers.txt", true)){
			StringBuilder builder = new StringBuilder(customer.getId());
			builder.append(","+customer.getName());
			builder.append(","+customer.getId());
			builder.append(","+customer.getPassword()+"\n");
			String customerString = new String(builder);
			writer.write(customerString);
		}catch(IOException e) {
			// log.error("Could not write to file: "+e.getMessage());
            System.out.println(e.getMessage());
		}	
	}

	public ArrayList<Customer> findAllCustomers() {
		ArrayList<Customer> allCustomers = new ArrayList<>();
		try(Scanner scan = new Scanner(new File("src//main//resources//Customers.txt"))) {
			while(scan.hasNextLine()) {
				String customerString = scan.nextLine();
				String[] customerParts = customerString.split(",");
				allCustomers.add(new Customer(customerParts[0], new Weapon(customerParts[1],
						Integer.valueOf(customerParts[2]), Integer.valueOf(customerParts[3]), 
						Element.valueOf(customerParts[4])), Integer.valueOf(customerParts[5]), Integer.valueOf(customerParts[6]),
						Integer.valueOf(customerParts[7]), Integer.valueOf(customerParts[8])));
			}
		}catch(IOException e) {
			// log.error("Something went wrong retieving players: "+e.getMessage());
            System.out.println(e.getMessage());
		}
		return allCustomers;
	}
}
