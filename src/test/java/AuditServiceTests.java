
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.models.*;
import com.revature.services.AuditService;
import com.revature.utils.ConnectionUtil;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditServiceTests {
    
    public static Logger log = LoggerFactory.getLogger(AccountServiceTests.class);

    public static AuditService auditServ; 
    public static String account_id;
    public static float balance;
    public static boolean active;
    public static String customer_id;
    public static Customer c;
    public static Account a;
    public static float amount;
    public static List<Audit> audits;
    String name;
    String password;
    String encryptPass;
    String audit_id;
    Account account;


    @BeforeAll
    public static void createObjects(){
        log.info("In createObjects()");
        auditServ = new AuditService();
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
        amount = 25;
        audits = new ArrayList<>();
        audit_id = "55555";
        c = new Customer(customer_id,name,password);
        account = new Account(account_id, balance, active, c);

        // control object - exists only in this file
        a = new Account(audit_id, balance, active, c); 

        // test object - writes to database
        auditServ.createAudit(audit_id, account);

        // both control and test are init with same values
        // they share the same id so db should recognize them as 
        // the same
    }

    @Test
    public void testCreateAudit(){
        /**
         * but is 'c' from the db or local?
         */
        log.info("In testCreateAudit()");
        auditServ.createAudit(audit_id, account);

        assertEquals(audit_id, account.getAccountID());
    }
 
	@Test
	public void testGetAllAudits(){
        log.info("In testGetAllAudits()");

        // remove all accounts
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "TRUNCATE TABLE audits CASCADE;";
                
            PreparedStatement statement = conn.prepareStatement(sql);
        
            statement.execute();
    
        }catch(SQLException e) {
            e.printStackTrace();
        }

        // add 3 new accounts (they don't seem to be added for some reason)
        auditServ.createAudit("54321", account);
        auditServ.createAudit("54322", account);
        auditServ.createAudit("54333", account);

        audits = auditServ.getAllAudits();

        // assert that all 3 accounts were retrieved by getAllAccounts()
        assertEquals(3, audits.size());
	}
}