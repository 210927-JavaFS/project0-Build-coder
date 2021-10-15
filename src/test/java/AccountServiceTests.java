
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.models.*;
import com.revature.services.AccountService;
import com.revature.utils.ConnectionUtil;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountServiceTests {
    
    public static Logger log = LoggerFactory.getLogger(AccountServiceTests.class);

    public static AccountService accountServ; 
    public static String account_id;
    public static float balance;
    public static boolean active;
    public static String customer_id;
    public static Customer c;
    public static Account a;
    public static float amount;
    public static List<Account> accounts;
    String name;
    String password;

    @BeforeAll
    public static void createObjects(){
        log.info("In createObjects()");
        accountServ = new AccountService();
    }

    @BeforeEach
    public void setVars(){
        log.info("In setVars()");
        account_id = "12345";
        balance = 50;
        active = false;
        name = "phil";
        customer_id = "10101";
        password = "dog";
        amount = 25;
        accounts = new ArrayList<>();
        c = new Customer(customer_id,name,password);

        // control object - exists only in this file
        a = new Account(account_id, balance, active, c); 

        // test object - writes to database
        accountServ.createAccount(account_id, balance, active, c);

        // both control and test are init with same values
        // they share the same id so db should recognize them as 
        // the same
    }

    @Test
    public void testCreateAccount(){
        log.info("In testCreateAccount()");

        /**
         * but is 'a' from the db or local?
         */
        accountServ.createAccount(account_id, balance, active, c);

        assertEquals(account_id, a.getAccountID());
    }
 
     @Test
     public void testAdd() {
        log.info("In testAdd()");

		accountServ.add(a, amount);
        /**
         * 'a' must be written to db b/c otherwise a.getBalance()
         * would equal balance, not balance + amount 
         */
        assertEquals((balance+amount), a.getBalance());
	}

    @Test
	public void testSubtract() {
        log.info("In testSubtract()");

		accountServ.subtract(a, amount);
        assertEquals((balance-amount), a.getBalance());
	}
    
	@Test
	public void testGetAllAccounts(){
        log.info("In testGetAllAccounts()");

	
        // remove all accounts
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "TRUNCATE TABLE accounts CASCADE;";
                
            PreparedStatement statement = conn.prepareStatement(sql);
        
            statement.execute();
    
        }catch(SQLException e) {
            e.printStackTrace();
        }

        // add 3 new accounts (they don't seem to be added for some reason)
        accountServ.createAccount("12345", balance, active, c);
        accountServ.createAccount("54321", balance, active, c);
        accountServ.createAccount("10101", balance, active, c);

        accounts = accountServ.getAllAccounts();

        // assert that all 3 accounts were retrieved by getAllAccounts()
        assertEquals(3,accounts.size());
	}

    @Test
	public void testRemove(){
        log.info("In testRemove()");

        // doesn't seem to be removing for some reason
		accountServ.remove(a);
        assertNotEquals(account_id, a.getAccountID());
        assertThrows(NullPointerException.class, () -> a.getAccountID());
	}

    @Test
	public void testUpdateAccountStatus(){
        log.info("In testUpdateAccountStatus");

		assertEquals(active, a.isActive());
	}
}
