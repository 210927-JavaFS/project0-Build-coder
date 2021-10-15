package com.revature.daos;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Audit;
import com.revature.utils.ConnectionUtil;

public class AuditDAOImpl implements AuditDAO {

    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public List<Audit> findAll() {

		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM audits;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Audit> list = new ArrayList<>();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			while(result.next()) {
				Audit audit = new Audit(
						result.getString("account_id"), 
						null
						);

				String account_id = result.getString("account_id");

				if(account_id!=null) {
					Account account = accountDAO.findByID(account_id);
					audit.setAccount(account);
				}
			
				list.add(audit);
			}
			
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		} 

		return null;
    }

    @Override
    public boolean addAudit(Audit a) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO audits (audit_id, account_id) "
					+ "VALUES (?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++count, a.getAudit_id());
			statement.setString(++count, a.getAccount().getAccountID());
			
			statement.execute();
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
    }

	@Override
	public boolean removeAudit(Audit a) {
		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "DELETE FROM audits WHERE audit_id = ?";
				
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setObject(1, a.getAudit_id());
		
			statement.execute();
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		} 
		
		return false;
	}
}
