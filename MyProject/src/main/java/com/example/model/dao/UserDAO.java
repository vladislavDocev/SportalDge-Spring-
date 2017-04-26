package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.example.model.User;

public class UserDAO {

	private static UserDAO instance;
	private static final HashMap<Integer, User> allUsers = new HashMap<>();// id
																			// ->
																			// user

	private UserDAO() {

	}

	public static synchronized UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public void addUser(User u) throws SQLException {
		String sql = "INSERT INTO user (username, password, email) values (?, ?, ?)";
		DBManager manager = DBManager.getInstance();
		Connection con = manager.getConnection();
		PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, u.getUsername());
		st.setString(2, u.getPassword());
		st.setString(3, u.getEmail());
		st.executeUpdate();
		System.out.println("Inserted into DB");
		ResultSet res = st.getGeneratedKeys();
		res.next();
		
		int id = res.getInt(1);
		u.setId(id);
		allUsers.put(u.getId(), u);
	}

	
	
	public HashMap<Integer, User> getAllUsers() throws SQLException {
		if (allUsers.isEmpty()) {
			String sql = "SELECT user_id, username, password , email, admin FROM user;";
			DBManager manager = DBManager.getInstance();
			Connection con = manager.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				User u = new User(res.getString("username"), res.getString("password"), res.getString("email"),
						res.getInt("admin"), res.getInt("user_id"));
				allUsers.put(u.getId(), u);
			}
		}
		return allUsers;
	}

	public synchronized boolean validLogin(String user, String pass) throws SQLException {
		if (getAllUsers().containsKey(user)) {
			return getAllUsers().get(user).getPassword().equals(pass);
		}
		return false;
	}
}
