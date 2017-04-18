package com.model;

public class User {
	
	private static int UNIQUE_ID = 1;
	private int id;
	private String name;
	private String userName;
	private String password;
	private String eMail;
	protected boolean isAdmin;
	
	public User(String name, String userName, String password, String eMail) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.eMail = eMail;
		this.id = UNIQUE_ID++;
		this.isAdmin = false;
	}

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getUserName() {
		return this.userName;
	}

	public boolean isAdmin() {
		return this.isAdmin;
	}

	public String geteMail() {
		return this.eMail;
	}
	
	public String getPass(){
		return this.password;
	}
}
