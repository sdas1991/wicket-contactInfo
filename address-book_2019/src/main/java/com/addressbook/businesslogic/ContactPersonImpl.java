package com.addressbook.businesslogic;

import com.addressbook.dto.ContactPerson;

public class ContactPersonImpl implements ContactInterface{

	@Override
	public ContactPerson getPerson(String name) {
		// TODO Auto-generated method stub
		ContactPerson cp=new ContactPerson();
		cp.setNamePerson("Ami");
		return cp;
	}

	@Override
	public ContactPerson getEmptyPerson() {
		// TODO Auto-generated method stub
		return new ContactPerson();
	}

	@Override
	public void saveContact(ContactPerson person) {
		// TODO Auto-generated method stub
		System.out.println("saved sucessfuly");
	}

	@Override
	public void deleteContact(ContactPerson person) {
		// TODO Auto-generated method stub
		System.out.println("deleted sucessfuly");
	}

}
