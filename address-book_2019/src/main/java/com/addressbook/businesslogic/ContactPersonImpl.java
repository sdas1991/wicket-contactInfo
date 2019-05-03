package com.addressbook.businesslogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.addressbook.datalayer.RedisImpl;
import com.addressbook.dto.ContactPerson;
import com.addressbook.dto.User;
import com.addressbook.landing.LoginPage;

public class ContactPersonImpl implements ContactService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER=LoggerFactory.getLogger(ContactPersonImpl.class);

	@Override
	public List<ContactPerson> getPerson(User user, String entryKey) {
		//create instance of data access layer
		RedisImpl reImpl=new RedisImpl();
		List<ContactPerson> list=new ArrayList<>();
		try {
			list=reImpl.searchContact(user, entryKey);
			LOGGER.info("called get contact on user: "+user.getEmail());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return list ;
	}

	@Override
	public ContactPerson getEmptyPerson() {
		
		ContactPerson cp=new ContactPerson();
		
		return cp;
	}

	
	@Override
	public boolean saveContact(User user, ContactPerson person) {
		
		RedisImpl redImpl=new RedisImpl();
		try {
			if (redImpl.addContact(user, person.getNamePerson(), person.getEmailAdd(), person.getPhoneNumber())) {
				LOGGER.info("called savecontact : person "+person.getNamePerson());	
				return true;
			} else {
				
				return false;

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			
			return false;
		}
		
		
		
		
	}

	@Override
	public void deleteContact(User user, ContactPerson person) {
		
		RedisImpl reImpl=new RedisImpl();

		try {
			
			
			reImpl.deleteContact(user, person);
			LOGGER.info("called delete : person "+person.getNamePerson());	
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
	}

	
	@Override
	public void updateEntry(User user, ContactPerson person,String newFirstName, String email, String phone)  throws Exception{
		
		RedisImpl redImpl=new RedisImpl();
			
		redImpl.updateContact(user, person, newFirstName, email, phone);
		LOGGER.info("called update : person "+person.getNamePerson());		
		
		
	}

}
