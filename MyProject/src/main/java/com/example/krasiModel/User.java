package com.example.krasiModel;

public class User {


	private static int UNIQUE_ID = 1;
	private int id;
	private String name;
	private String username;
	private String password;
	private String email;
	protected boolean isAdmin;
	
	public User(String name, String username, String password, String email) {
		this(username,password);
		this.name = name;
		this.email = email;
		this.id = UNIQUE_ID++;
		this.isAdmin = false;
	}

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getUsername() {
		return this.username;
	}

	public boolean isAdmin() {
		return this.isAdmin;
	}

	public String getEmail() {
		return this.email;
	}
	
	public String getPassword(){
		return this.password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "User [name=" + name + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
}
