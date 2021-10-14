
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.daos.AccountDAOImpl;
import com.revature.models.*;
import com.revature.services.AccountService;
import com.revature.utils.ConnectionUtil;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountServiceTests {
    
    public static AccountService accountServ; 
    public static Logger log = LoggerFactory.getLogger(AccountServiceTests.class);

    public static String account_id = "12345";
    public static float balance = 0;
    public static boolean active = false;
    public static String customer_id;
    public static Customer c = null;

    @BeforeAll
    public static void setAccountServ(){
        log.info("In setAccountServ()");
        accountServ = new AccountService();
    }

    @BeforeEach
    public void setVars(){
        log.info("In setVars()");
        account_id = "12345";
        balance = 0;
        active = false;
        customer_id = "10101";
        c.setId("10101");
        c.setName("phil");
        c.setPassword("dog");
    }

    @Test
	public void testCreateAccount() {
        log.info("In testCreateAccount()");
        
        // object existing only in this scope
        Account a = new Account(account_id, balance, active, c);

        // object being written to the database
        accountServ.createAccount(account_id, balance, active, c);
        
        try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM accounts WHERE account_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, account_id);
			
			ResultSet result = statement.executeQuery();
			
			Account account = new Account();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) 
            //that can be used with a while loop to iterate through all the data. 
			
			if(result.next()) {
				
				account.setAccountID(result.getString("account_id"));
				account.setBalance(result.getFloat("account_balance"));
				account.setActive(result.getBoolean("account_active"));
				account.setCustomerID(result.getString("customer_id"));
			}
			
            // assert that object in scope is equal to object in database
			assertEquals(a, account);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
     @Test
	 public void add() {
        log.info("In add()");

	 	assertEquals(1,1);
	 }

    // @Test
	// public void subtract(Account account, int amount) {
	// 	account.setBalance(account.getBalance() - amount);
	// }

    // @Test
	// public void updateAccount(Account x){
	// 	// if account == null, init account
		
	// }

    // @AfterEach
    // public void clearResults(){

    // }

    // @AfterAll
    // public static void clearAccountServ(){
    //     accountServ = null;
    //     log.info("in clearAccountServ");
    // }

}
