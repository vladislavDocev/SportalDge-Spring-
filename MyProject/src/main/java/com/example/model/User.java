package com.example.model;

import java.sql.SQLException;

import com.example.controller.MyController;

public class User {


	private int id;
	private String name;
	private String username;
	private String password;
	private String email;
	protected int isAdmin;
	
	public User(String username, String password, String email, int admin) {
		this(username,password);
		this.email = email;
		this.isAdmin = admin;
	}

	public User(String username, String password, String email, int admin, int id) {
		this(username,password,email,admin);
		this.id = id;
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

	public int isAdmin() {
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
		return "User [id=" + id + ", username=" + username + "]";
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static boolean validUser(String username,String email, String password,
			String confirmPassword) throws SQLException {
		return validText(username)&& validEmail(email) && validPassword(password)
				&& validPassword(confirmPassword) && validPasswords(password, confirmPassword);
	}
	
	public static boolean validText(String name) {
		if(name == null){
			return false;
		}
		return !name.trim().isEmpty();
	}

	public static boolean validEmail(String email) throws SQLException {
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		return email.matches(regex) && !MyController.USER_DAO.getAllUsers().containsKey(email);
	}
	
	public static boolean validPassword(String passwordFirst) {
		if(passwordFirst == null){
			return false;
		}
		return !passwordFirst.trim().isEmpty();
	}
	
	public static boolean validPasswords(String passwordFirst, String passwordSecond) {
		if(!validPassword(passwordFirst) || !validPassword(passwordSecond)){
			return false;
		}
		return passwordFirst.equals(passwordSecond);
	}
}
