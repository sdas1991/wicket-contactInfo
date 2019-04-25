package com.addressbook.datalayer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import redis.clients.jedis.Jedis;

public class RedisImpl {
	
	
    private static Jedis jedis = new Jedis("localhost");
    
   /* private static void auth() {
        String session = jedis.auth(authKey);
}*/
    public static boolean saveUser(String username, String password) {
        
        if(!jedis.exists("user:" + username)) {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return false;
            }
            //Change the byte[] from MessageDigest into a string
            String hashPass = Base64.getEncoder().encodeToString(digest.digest(password.getBytes()));
            jedis.hset("user:" + username, "password", hashPass);
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
        //Change the byte[] from MessageDigest into a string
        String hashPass = Base64.getEncoder().encodeToString(digest.digest(password.getBytes()));
        if(jedis.hexists("user:" + username, "password")) {
            return (jedis.hget("user:" + username, "password").equals(hashPass));
        } else {
            return false;
        }
    }
    
    
    
    

}
