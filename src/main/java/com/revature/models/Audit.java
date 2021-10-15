package com.revature.models;

public class Audit {
    
    private String audit_id;
    private Account account;

    public Audit(String audit_id, Account account){
        super();
        this.audit_id = audit_id;
        this.account = account;
    }

    public Audit(){
        super();
    }

    public String getAudit_id() {
        return audit_id;
    }

    public void setAudit_id(String audit_id) {
        this.audit_id = audit_id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((audit_id == null) ? 0 : audit_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Audit other = (Audit) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (audit_id == null) {
            if (other.audit_id != null)
                return false;
        } else if (!audit_id.equals(other.audit_id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Audit [account=" + account + ", audit_id=" + audit_id + "]";
    }    
}