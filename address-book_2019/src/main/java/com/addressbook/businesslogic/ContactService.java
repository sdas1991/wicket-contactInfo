package com.addressbook.businesslogic;

import java.util.List;

import com.addressbook.dto.ContactPerson;

public interface ContactService {
	
	public List<ContactPerson> getPerson(String name);
	
	public ContactPerson getEmptyPerson();
	
	public void saveContact(ContactPerson person);
	
	public void deleteContact(ContactPerson person);

}
