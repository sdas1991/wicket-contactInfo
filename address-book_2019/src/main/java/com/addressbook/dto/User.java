package com.addressbook.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User extends ModelBase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4670086175830616561L;
	
	private String id;
	private String emailId;
	private String password;
	private List<ContactPerson> contactPerson;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<ContactPerson> getContactPerson() {
		if(contactPerson==null) {
			contactPerson=new ArrayList<ContactPerson>();
		}
		return contactPerson;
	}
	public void setContactPerson(List<ContactPerson> contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String emailId, String password) {
		this.emailId=emailId;
		this.password=password;
	}
	
	public String getEmail() {
		return emailId;
	}
	public void setEmail(String email) {
		this.emailId = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb=new StringBuilder();
		sb.append("User Model: email="+emailId);
		return sb.toString();
		
	}
	
	
	
	

}
