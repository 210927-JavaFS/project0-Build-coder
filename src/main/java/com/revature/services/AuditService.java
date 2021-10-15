package com.revature.services;

import java.util.List;

import com.revature.daos.AuditDAOImpl;
import com.revature.models.Account;
import com.revature.models.Audit;

public class AuditService {
    
    private static AuditDAOImpl auditDAO = new AuditDAOImpl();

    public void createAudit(String id, Account a){
        Audit audit = new Audit(id, a);
        auditDAO.addAudit(audit);
    }

    public List<Audit> getAllAudits(){
        return auditDAO.findAll();
    }

    public void remove(Audit a){
        auditDAO.removeAudit(a);
    }
}
