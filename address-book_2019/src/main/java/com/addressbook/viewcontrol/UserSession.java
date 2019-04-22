package com.addressbook.viewcontrol;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.addressbook.dto.User;

public class UserSession extends WebSession{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -514002815420596467L;
	
	private User user;
	public UserSession(Request request) {
		
		super(request);
	}
	public static UserSession getInstance() {
		return (UserSession) Session.get();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
