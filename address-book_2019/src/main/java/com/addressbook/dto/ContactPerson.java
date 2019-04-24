package com.addressbook.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactPerson extends ModelBase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1879372893218034336L;
	private String id;
	private String namePerson;
	private String phoneNumber;
	private String emailAdd;
	
	
	public ContactPerson() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ContactPerson(String namePerson, String phoneNumber, String emailAdd) {
		// TODO Auto-generated constructor stub
		super();
		this.namePerson=namePerson;
		this.phoneNumber=phoneNumber;
		this.emailAdd=emailAdd;
		
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamePerson() {
		return namePerson;
	}
	public void setNamePerson(String namePerson) {
		this.namePerson = namePerson;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAdd() {
		return emailAdd;
	}
	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}
	
	
	

}
