package com.addressbook.businesslogic;

import java.io.Serializable;
import java.security.SecureRandom;

import com.addressbook.datalayer.RedisImpl;
import com.addressbook.dto.User;

public class UserServiceImpl implements UserService, Serializable{
	
	private final String alphaNum="ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

	@Override
	public User getEmptyUser() {
		// TODO Auto-generated method stub
		return new User();
	}

	@Override
	public void saveUser(User user) {
		//set User id
		String id=generateUserId();
		
		user.setId(id);
		
		System.out.println(user.getId());
		
	}

	@Override
	public boolean validateUser(User user) {
		// TODO Auto-generated method stub
		user.getEmail();
		
		RedisImpl redisClient=new RedisImpl();
		boolean authorised=redisClient.validateUser(user.getEmail(), user.getPassword());
		return true;
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
