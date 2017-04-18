package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.model.User;
import com.model.exceptions.InvalidEmailException;
import com.model.exceptions.InvalidNameException;
import com.model.exceptions.InvalidPasswordException;
import com.model.exceptions.InvalidUsernameException;

public class UserDAO {

	private static UserDAO instance;
	private static final HashMap<String, User> allUsers = new HashMap<>();// username -> user
	private UserDAO() {

	}

	public static synchronized UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public void addUser(User u) throws SQLException {
		// TODO insert into DB
		String sql = "INSERT INTO user (name, username, password, email) values (?, ?, ?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, u.getName());
		st.setString(2, u.getUserName());
		st.setString(3, u.getPass());
		st.setString(4, u.geteMail());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		long id = res.getLong(1);
		allUsers.put(u.getUserName(), u);
	}

	public HashMap<String, User> getAllUsers() throws SQLException {
		if (allUsers.isEmpty()) {
			String sql = "SELECT user_id, name, username, password , email FROM user;";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				User u = new User(res.getString("name"), res.getString("username"), res.getString("password"),
						res.getString("email"));
				allUsers.put(u.getUserName(), u);
			}
		}
		return allUsers;
	}

	public synchronized boolean validLogin(String user, String pass) throws SQLException {
		if (getAllUsers().containsKey(user)) {
			return getAllUsers().get(user).getPass().equals(pass);
		}
		return false;
	}
}
