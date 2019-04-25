package com.addressbook.businesslogic;

import com.addressbook.dto.User;

//Business Layer Interface
public interface UserService {
	
	public User getEmptyUser();
	
	public boolean saveUser(User user);
	
	public boolean validateUser(User user);

}
