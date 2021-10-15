package com.revature.daos;

import java.util.List;

import com.revature.models.Audit;

public interface AuditDAO {
    
    public List<Audit> findAll();

    public boolean addAudit(Audit a);
}
