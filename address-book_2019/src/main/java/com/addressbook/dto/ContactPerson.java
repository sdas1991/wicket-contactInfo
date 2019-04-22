package com.addressbook.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactPerson extends ModelBase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1879372893218034336L;
	private String namePerson;
	private String phoneNumber;
	private String emailAdd;
	private List<Address> address;
	
	public ContactPerson() {
		// TODO Auto-generated constructor stub
	}
	
	public ContactPerson(String namePerson, String phoneNumber, String emailAdd) {
		// TODO Auto-generated constructor stub
		this.namePerson=namePerson;
		this.phoneNumber=phoneNumber;
		this.emailAdd=emailAdd;
		
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
	public List<Address> getAddress() {
		if(address==null) {
			address=new ArrayList<>();
		}
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	
	

}
