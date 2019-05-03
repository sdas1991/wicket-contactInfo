package com.addressbook.businesslogic;

import java.util.List;

import com.addressbook.dto.ContactPerson;
import com.addressbook.dto.User;
//Busines Layer Interface for Contact person
public interface ContactService {
	
	public List<ContactPerson> getPerson(User user, String entryKey);
	
	public ContactPerson getEmptyPerson();
	
	public boolean saveContact(User user, ContactPerson person);
	
	public void deleteContact(User user, ContactPerson contact);
	
	public void updateEntry(User user, ContactPerson person,String newFirstName, String email, String phone) throws Exception;
	
	

}
