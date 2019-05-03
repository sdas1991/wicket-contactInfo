package com.addressbook.datalayer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.addressbook.businesslogic.ContactPersonImpl;
import com.addressbook.dto.ContactPerson;
import com.addressbook.dto.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ZAddParams;

public class RedisImpl {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(RedisImpl.class);

	private static Jedis jedis = new Jedis("localhost");

	public boolean saveUser(String username, String password) {

		if (!jedis.exists("user:" + username)) {
			MessageDigest digest = null;
			try {
				digest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return false;
			}
			
			String pass = Base64.getEncoder().encodeToString(digest.digest(password.getBytes()));			
			jedis.hset("user:" + username, "password", pass);
			
			return true;
		} else {
			return false;
		}
	}

	public boolean validateUser(String username, String password) {

		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
		
		String pass = Base64.getEncoder().encodeToString(digest.digest(password.getBytes()));
		
		if (jedis.hexists("user:" + username, "password")) {
			return (jedis.hget("user:" + username, "password").equals(pass));
		} else {
			return false;
		}
	}

	public boolean addContact(User user, String name, String email, String phone) {

		if (user == null || name == null || email == null)
			return false;

		String listKey = user.getEmail() + ":contactList";
		String entryKey = name.toLowerCase().replaceAll(" ", "");

		boolean isNewKey = (jedis.zadd(listKey, 0, entryKey, ZAddParams.zAddParams().nx()) == 1);
		LOGGER.info("is new key boolean while adding: "+isNewKey);
		
		int duplicateIndex = 0;
		while (!isNewKey) {
			duplicateIndex++;
			isNewKey = (jedis.zadd(listKey, 0, entryKey + duplicateIndex, ZAddParams.zAddParams().nx()) == 1);
		}

		if (duplicateIndex != 0)
			entryKey = entryKey + duplicateIndex;

		String hKey = user.getEmail() + ":" + entryKey;
		jedis.hset(hKey, "Name", name);
		jedis.hset(hKey, "email", email);
		jedis.hset(hKey, "index", Integer.toString(duplicateIndex));
		jedis.hset(hKey, "phone", phone);
			

		return true;
	}
	
    public ContactPerson getContact(User user, String entryKey) {
       
        String hKey = user.getEmail() + ":" + entryKey;
        
        LOGGER.info("hkey while getting contact "+hKey);
        
        Map<String, String> value = jedis.hgetAll(hKey);
        
        String firstName = value.get("Name");
        String email = value.get("email");
        String phone = value.get("phone");
        int id = Integer.parseInt(value.get("index"));
        return (new ContactPerson(id,firstName, phone,email));
    }
    
    public List<ContactPerson> searchContact(User user, String search) {
        
        if(search.contains(" "))
            search = search.replaceAll(" ", "");

        String listKey = user.getEmail() + ":contactList";
        String min = "[" + search.toLowerCase();
        
        String max = "[" + search.toLowerCase() + "~";
        String[] resultKeys = jedis.zrangeByLex(listKey, min, max).toArray(new String[0]);
        
        LOGGER.info("search person by lex : "+jedis.zrangeByLex(listKey, min, max));
        List<ContactPerson> result = new ArrayList<ContactPerson>(resultKeys.length);
        for(int i = 0; i < resultKeys.length; i++) {
        	result.add(getContact(user, resultKeys[i]));
        	
        }
            
        return result;
    }
    
    public void updateContact(User user, ContactPerson person, String newFirstName, String email, String phone) {
        
        if(person.getNamePerson().equalsIgnoreCase(newFirstName)) {
        	
        	LOGGER.info("person name already object from front "+person.getNamePerson());
        	LOGGER.info("person name new object "+newFirstName);
        	         
        	String hkey = user.getEmail() + ":" + person.getKeyString();
           
            jedis.hset(hkey, "Name", newFirstName);
            jedis.hset(hkey, "email", email);
         
            jedis.hset(hkey, "phone", phone);
        } else {
        	LOGGER.info("inside else of update as name is different "+newFirstName);     	
        	deleteContact(user, person);
            addContact(user, newFirstName, email, phone);
        }

    }
    
    public void deleteContact(User user, ContactPerson contact) {
        
        String keyName = contact.getKeyString();
       LOGGER.debug("keyname in delete "+keyName);
        
       //remove from list 
       jedis.zrem(user.getEmail() + ":contactList", keyName);
        //remove key      
       jedis.del(user.getEmail() + ":" + keyName);
        
    }

}
