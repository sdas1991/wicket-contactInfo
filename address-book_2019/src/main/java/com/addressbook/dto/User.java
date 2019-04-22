package com.addressbook.dto;

import java.io.Serializable;
import java.util.List;

public class User extends ModelBase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4670086175830616561L;
	
	private String name;
	private String email;
	private String password;
	private List<ContactPerson> contactPerson;
	
	
	
	public List<ContactPerson> getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(List<ContactPerson> contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String email, String password) {
		this.email=email;
		this.password=password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	

}
