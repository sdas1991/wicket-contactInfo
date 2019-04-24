package com.addresbook.dao;

public class ContactPersonDAO extends ModelDAO{
	
	private String id;
	private String namePerson;
	private String phoneNumber;
	private String emailAdd;
	private String isPurged;
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
	public String getIsPurged() {
		return isPurged;
	}
	public void setIsPurged(String isPurged) {
		this.isPurged = isPurged;
	}
	
	
	
	
	

}
