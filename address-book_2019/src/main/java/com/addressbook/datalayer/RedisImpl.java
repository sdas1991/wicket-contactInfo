package com.addressbook.datalayer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import com.addressbook.dto.ContactPerson;
import com.addressbook.dto.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ZAddParams;

public class RedisImpl {

	private static Jedis jedis = new Jedis("localhost");

	/*
	 * private static void auth() { String session = jedis.auth(authKey); }
	 */
	public static boolean saveUser(String username, String password) {

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

	public static boolean validateUser(String username, String password) {

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

	public static boolean addContact(User user, String name, String email, String phone) {

		if (user == null || name == null || email == null)
			return false;

		

		String listKey = user.getEmail() + ":contactList";
		String entryKey = name.toLowerCase().replaceAll(" ", "");

		boolean isNewKey = (jedis.zadd(listKey, 0, entryKey, ZAddParams.zAddParams().nx()) == 1);

		int duplicateIndex = 0;
		while (!isNewKey) {
			duplicateIndex++;
			isNewKey = (jedis.zadd(listKey, 0, entryKey + duplicateIndex, ZAddParams.zAddParams().nx()) == 1);
		}

		if (duplicateIndex != 0)
			entryKey = entryKey + duplicateIndex;

		String hKey = user.getEmail() + ":" + entryKey;
		//System.out.println("hkey while inserting "+hKey);
		jedis.hset(hKey, "Name", name);
		jedis.hset(hKey, "email", email);
		jedis.hset(hKey, "index", Integer.toString(duplicateIndex));
		if (phone != null)
			jedis.hset(hKey, "phone", phone);

		return true;
	}
	
    public static ContactPerson getContact(User user, String entryKey) {
       
        String hKey = user.getEmail() + ":" + entryKey;
        System.out.println("user email in redis while getting "+user.getEmail() );
        System.out.println("hkey while geting "+hKey);
        Map<String, String> value = jedis.hgetAll(hKey);
        
        String firstName = value.get("Name");
        
        String email = value.get("email");
        String phone = value.get("phone");
        int id = Integer.parseInt(value.get("index"));
        return (new ContactPerson(id,firstName, phone,email));
    }
    
    public static List<ContactPerson> searchEntries(User user, String search) {
        
        if(search.contains(" "))
            search = search.replaceAll(" ", "");

        String listKey = user.getEmail() + ":contactList";
        String min = "[" + search.toLowerCase();
        
        String max = "[" + search.toLowerCase() + "~";
        String[] resultKeys = jedis.zrangeByLex(listKey, min, max).toArray(new String[0]);
        List<ContactPerson> result = new ArrayList<ContactPerson>(resultKeys.length);
        for(int i = 0; i < resultKeys.length; i++)
            result.add(getContact(user, resultKeys[i])) ;
        return result;
    }
    
    public static void updateEntry(User user, ContactPerson person, String newFirstName, String email, String phone) {
        
        if(person.getNamePerson().equalsIgnoreCase(newFirstName)) {
        	//System.out.println("in redis keyString "+person.getKeyString());
            
        	String hkey = user.getEmail() + ":" + person.getKeyString();
           
            // System.out.println("phone number inside redis "+phone);
            //System.out.println("in redis hkey while updating "+hkey);
            
            jedis.hset(hkey, "Name", newFirstName);
            if(email != null)
                jedis.hset(hkey, "email", email);
            if(phone != null)
                jedis.hset(hkey, "phone", phone);
        } else {
            
        	
        	
        	deleteContact(user, person);
            addContact(user, newFirstName, email, phone);
        }

    }
    
    public static void deleteContact(User user, ContactPerson contact) {
        
        String keyName = contact.getKeyString();
        System.out.println("keyname in delete "+keyName);
        System.out.println("Hsk key in delete "+user.getEmail() + ":contactList"+keyName);
        jedis.zrem(user.getEmail() + ":contactList", keyName);
        jedis.del(user.getEmail() + ":" + keyName);
    }

}
