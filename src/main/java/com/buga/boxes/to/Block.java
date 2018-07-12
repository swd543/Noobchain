package com.buga.boxes.to;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buga.boxes.security.HashBuilder;

public class Block <T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7678860692282059687L;
	private final String hash, previousHash;
	private final T object;
	
	private final Logger logger=LoggerFactory.getLogger(getClass());

	public String calculateHash() throws NoSuchAlgorithmException, IOException {
		return new HashBuilder().build(getObject());
	}
	
	public String getHash() {
		return hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}
	
	public T getObject() {
		return object;
	}
	
	public Block(String hash, String previousHash, T object) {
		super();
		this.hash = hash;
		this.previousHash = previousHash;
		this.object = object;
		logger.info("Created "+toString());
	}
	
	public Block(String previousHash, T object) throws NoSuchAlgorithmException, IOException {
		super();
		this.hash = new HashBuilder().build(object);
		this.previousHash = previousHash;
		this.object = object;
		logger.info("Created "+toString());
	}

	@Override
	public final String toString() {
		return "Block [hash=" + hash + ", previousHash=" + previousHash + ", object=" + object
				+ "]";
	}
}
