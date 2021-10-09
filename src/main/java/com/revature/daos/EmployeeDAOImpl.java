package com.revature.daos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Employee;

public class EmployeeDAOImpl {
	
	 private static Logger log = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	 private final String txtFile = "./src/main/resources/customer.txt";

	public void writeProfile(Employee profile) {
		File profiles = new File(txtFile);
		
		try {
			if(profiles.createNewFile()) {
				 log.info("Created new players file");
//                System.out.println("Created new players file");
			}else {
				 log.info("players file already exists");
//                System.out.println("players file already exists");
			}
		}catch(IOException e) {
			 log.error("Something went wrong trying to access players file: "+e.getMessage());
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

// 	public ArrayList<Account> findAllAccounts() {
// 		ArrayList<Account> allAccounts = new ArrayList<>();
// 		try(Scanner scan = new Scanner(new File("src//main//resources//accounts.txt"))) {
// 			while(scan.hasNextLine()) {
// 				String accountString = scan.nextLine();
// 				String[] accountParts = accountString.split(",");
// 				allAccounts.add(new Account(accountParts[0], new Weapon(accountParts[1],
// 						Integer.valueOf(accountParts[2]), Integer.valueOf(accountParts[3]), 
// 						Element.valueOf(accountParts[4])), Integer.valueOf(accountParts[5]), Integer.valueOf(accountParts[6]),
// 						Integer.valueOf(accountParts[7]), Integer.valueOf(accountParts[8])));
// 			}
// 		}catch(IOException e) {
// 			 log.error("Something went wrong retieving players: "+e.getMessage());
// //            System.out.println(e.getMessage());
// 		}
// 		return allAccounts;
// 	}
}
