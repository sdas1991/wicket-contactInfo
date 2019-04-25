package com.addressbook.businesslogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.addressbook.dto.ContactPerson;

public class ContactPersonImpl implements ContactService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<ContactPerson> getPerson(String name) {
		// TODO Auto-generated method stub
		ContactPerson cp=new ContactPerson();
		cp.setNamePerson("Ami");
		
		List<ContactPerson> cpList=new ArrayList<>();
		cpList.add(cp);
		
		
		return cpList;
	}

	@Override
	public ContactPerson getEmptyPerson() {
		// TODO Auto-generated method stub
		ContactPerson cp=new ContactPerson();
		cp.setEmailAdd("dasgmail@gmail.com");
		cp.setNamePerson("sourangshu");
		cp.setPhoneNumber("4094446236");
		return cp;
	}

	@Override
	public void saveContact(ContactPerson person) {
		// TODO Auto-generated method stub
		System.out.println("saved sucessfuly");
	}

	@Override
	public void deleteContact(ContactPerson person) {
		// TODO Auto-generated method stub
		System.out.println(person.getNamePerson());
		System.out.println("deleted sucessfuly");
	}

}
