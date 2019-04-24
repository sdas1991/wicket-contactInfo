package com.addressbook.businesslogic;

import com.addressbook.dto.ContactPerson;

public interface ContactInterface {
	
	public ContactPerson getPerson(String name);
	
	public ContactPerson getEmptyPerson();
	
	public void saveContact(ContactPerson person);
	
	public void deleteContact(ContactPerson person);

}
