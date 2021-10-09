
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.services.AccountService;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountServiceTests {
    
    public static AccountService accountServ; 
    public static int i, j, k, result;
    public static Logger log = LoggerFactory.getLogger(AccountServiceTests.class);

    @BeforeAll
    public static void setAccountServ(){
        log.info("In method 'setAccountServ'");
        accountServ = new AccountService();
    }

    @BeforeEach
    public void setVars(){
        i = 7;
        j = 5;
        k = 0;
    }
    

    @Test
	public Account createAccount(String name, String accountID, int balance, boolean active) {
        log.info("In method 'createAccount'");
		return new Account(name, accountID, balance, active);
	}
 
    @Test
	public void add(Account account, int amount) {
		account.setBalance(account.getBalance() + amount);
	}

    @Test
	public void subtract(Account account, int amount) {
		account.setBalance(account.getBalance() - amount);
	}
    @Test

    public void addToList(Account x, ArrayList<Account>bankAccounts){
		// allAccounts.add(x);
		bankAccounts.add(x);
    }

    @Test
	public void updateAccount(Account x){
		// if account == null, init account
		
	}

    @Test
	public void save(Account account) {
		AccountDAOImpl accountDAO = new AccountDAOImpl();
        accountDAO.writeAccount(account);
	}

}
