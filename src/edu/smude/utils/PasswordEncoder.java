package edu.smude.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordEncoder {
    public static String getEncoded(String password){

        String md5password = "";
        MessageDigest md5 ;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes(), 0, password.length());

            md5password = new BigInteger(1, md5.digest()).toString(16);
            
        } catch(Exception e ){
            e.printStackTrace();
        }

        return md5password;
    }
}
