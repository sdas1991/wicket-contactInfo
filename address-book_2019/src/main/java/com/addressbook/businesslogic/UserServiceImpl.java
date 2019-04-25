package com.addressbook.businesslogic;

import java.io.Serializable;
import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.addressbook.datalayer.RedisImpl;
import com.addressbook.dto.User;
import com.addressbook.landing.LoginPage;
@SuppressWarnings("static-access")
public class UserServiceImpl implements UserService, Serializable{
	private static final Logger LOGGER=LoggerFactory.getLogger(LoginPage.class);
	private final String alphaNum="ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

	@Override
	public User getEmptyUser() {
		// TODO Auto-generated method stub
		return new User();
	}

	
	@Override
	public boolean saveUser(User user) {
		//set User id
		String id=generateUserId();
		RedisImpl redisClient=new RedisImpl();
		user.setId(id);
		try {
			
			//Save the user on sign up
			if(redisClient.saveUser(user.getEmail(), user.getPassword()))
			{
				return true;
			}else {
				return false;
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return false;
		}
		
		
		
		
	}

	@Override
	public boolean validateUser(User user) {
		// validate the user on login
		user.getEmail();
		
		RedisImpl redisClient=new RedisImpl();
		boolean authorised=redisClient.validateUser(user.getEmail(), user.getPassword());
		return authorised;
	}
	
	private String generateUserId() {
		SecureRandom rnd=new SecureRandom();
		int len=6;
		StringBuilder uniqueId=new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			uniqueId.append(alphaNum.charAt(rnd.nextInt(alphaNum.length())));
		}		
		
		return uniqueId.toString();
	}

}
