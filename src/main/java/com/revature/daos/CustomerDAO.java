package com.revature.daos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

import com.revature.models.Customer;

public class CustomerDAO {
	
	private static Logger log = LoggerFactory.getLogger(CustomerDAO.class);
	public String txtFile = "./src/main/resources/customer.txt";
	public void writeProfile(Customer profile) {
		File profiles = new File(txtFile);
		
		try {
			if(profiles.createNewFile()) {
				 log.info("Created new customer file");
//                System.out.println("Created new players file");
			}else {
				 log.info("customer file already exists");
//                System.out.println("players file already exists");
			}
		}catch(IOException e) {
			 log.error("Something went wrong trying to access customer file: "+e.getMessage());
//            System.out.println(e.getMessage());
		}
		
		try(FileWriter writer = new FileWriter(txtFile, true)){
			StringBuilder builder = new StringBuilder(profile.getId());
			builder.append(","+profile.getName());
			builder.append(","+profile.getId());
			builder.append(","+profile.getPassword()+"\n");
			String profileString = new String(builder);
			writer.write(profileString);
		}catch(IOException e) {
			 log.error("Could not write to file: "+e.getMessage());
//            System.out.println(e.getMessage());
		}	
	}

// 	public ArrayList<Customer> findAllCustomers() {
// 		ArrayList<Customer> allCustomers = new ArrayList<>();
// 		try(Scanner scan = new Scanner(new File("src//main//resources//Customers.txt"))) {
// 			while(scan.hasNextLine()) {
// 				String customerString = scan.nextLine();
// 				String[] customerParts = customerString.split(",");
// 				allCustomers.add(new Customer(customerParts[0], new Weapon(customerParts[1],
// 						Integer.valueOf(customerParts[2]), Integer.valueOf(customerParts[3]), 
// 						Element.valueOf(customerParts[4])), Integer.valueOf(customerParts[5]), Integer.valueOf(customerParts[6]),
// 						Integer.valueOf(customerParts[7]), Integer.valueOf(customerParts[8])));
// 			}
// 		}catch(IOException e) {
// 			 log.error("Something went wrong retieving players: "+e.getMessage());
// //            System.out.println(e.getMessage());
// 		}
// 		return allCustomers;
// 	}
}
