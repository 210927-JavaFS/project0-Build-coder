
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.models.*;
import com.revature.services.CustomerService;
import com.revature.utils.ConnectionUtil;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerServiceTests {
    
    public static Logger log = LoggerFactory.getLogger(AccountServiceTests.class);

    public static CustomerService customerServ; 
    public static String account_id;
    public static float balance;
    public static boolean active;
    public static String customer_id;
    public static Customer c;
    public static Account a;
    public static float amount;
    public static List<Customer> profiles;
    String name;
    String password;
    String encryptPass;

    @BeforeAll
    public static void createObjects(){
        log.info("In createObjects()");
        customerServ = new CustomerService();
    }

    @BeforeEach
    public void setVars(){
        log.info("In setVars()");
        account_id = "12345";
        balance = 50;
        active = false;
        customer_id = "10101";
        name = "phil";
        password = "dog";
        encryptPass = "god";
        amount = 25;
        profiles = new ArrayList<>();
        c = new Customer(customer_id,name,password);

        // control object - exists only in this file
        a = new Account(account_id, balance, active, c); 

        // test object - writes to database
        customerServ.createAccount(customer_id, name, password, encryptPass);

        // both control and test are init with same values
        // they share the same id so db should recognize them as 
        // the same
    }

    @Test
    public void testCreateProfile(){
        /**
         * but is 'c' from the db or local?
         */
        log.info("In testCreateProfile()");
        customerServ.createAccount(customer_id, name, password, encryptPass);

        assertEquals(customer_id, c.getId());
    }
 
	@Test
	public void testGetAllProfiles(){
        log.info("In testGetAllProfiles()");

        // remove all accounts
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "TRUNCATE TABLE customers CASCADE;";
                
            PreparedStatement statement = conn.prepareStatement(sql);
        
            statement.execute();
    
        }catch(SQLException e) {
            e.printStackTrace();
        }

        // add 3 new accounts (they don't seem to be added for some reason)
        customerServ.createAccount("12345", "bob", "dog", "god");
        customerServ.createAccount("54321", "bill", "dog", "god");
        customerServ.createAccount("11234", "tim", "dog", "god");

        profiles = customerServ.getAllProfiles();

        // assert that all 3 accounts were retrieved by getAllAccounts()
        assertEquals(3, profiles.size());
	}

    @Test
	public void testRemove(){
        log.info("In testRemove()");

        // doesn't seem to be removing for some reason
		customerServ.remove(c);
        assertNotEquals(customer_id, c.getId());
        assertThrows(NullPointerException.class, () -> c.getId());
	}
}
