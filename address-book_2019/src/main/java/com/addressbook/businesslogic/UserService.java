package com.addressbook.businesslogic;

import com.addressbook.dto.User;

public interface UserService {
	
	public User getEmptyUser();
	
	public void saveUser(User user);
	
	public boolean validateUser(User user);

}
