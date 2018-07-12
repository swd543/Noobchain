package com.buga.boxes.to;

import java.io.Serializable;

public class DataTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6222827250031157620L;
	private String name;
	private long timestamp;
	
	public DataTO() {
		this.timestamp=System.nanoTime();
	}
	
	public DataTO(String name) {
		super();
		this.name = name;
		this.timestamp = System.nanoTime();
	}
	
	public DataTO(String name, long timestamp) {
		super();
		this.name = name;
		this.timestamp = timestamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "DataTO [name=" + name + ", timestamp=" + timestamp + "]";
	}
}
