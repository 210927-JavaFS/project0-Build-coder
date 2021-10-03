package com.revature.models;

public class Customer {
    private String name;
    private String password;
    private String id;

    public Customer(String name, String password, String id){
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public Customer(){
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassoword() {
        return password;
    }
    public void setPassoword(String passoword) {
        this.password = passoword;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
