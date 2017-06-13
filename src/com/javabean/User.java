package com.javabean;

import java.io.InputStream;

public class User {

	private int uid;
	private String username;
	private String password;
	private InputStream uimage;
	private String uantograph;
	private String uemail;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public InputStream getUimage() {
		return uimage;
	}
	public void setUimage(InputStream blob) {
		this.uimage = blob;
	}
	public String getUantograph() {
		return uantograph;
	}
	public void setUantograph(String uantograph) {
		this.uantograph = uantograph;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	} 
	
	
}
