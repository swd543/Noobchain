package com.buga.boxes.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashBuilder {
	public <T> String build(T object) throws NoSuchAlgorithmException, IOException {
		MessageDigest digest=MessageDigest.getInstance("SHA-256");
		return hexify(digest.digest(object.toString().getBytes()));
	}
	
	private String hexify(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
}
