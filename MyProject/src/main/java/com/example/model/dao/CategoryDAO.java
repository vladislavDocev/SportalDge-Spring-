package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.example.model.Category;


public class CategoryDAO {
	private static CategoryDAO instance;
	private static final HashMap<Integer, Category> allCategories= new HashMap<>();
	private CategoryDAO() {

	}

	public static synchronized CategoryDAO getInstance() {
		if (instance == null) {
			instance = new CategoryDAO();
		}
		return instance;
	}

	public void addCategory(Category m) throws SQLException {
		String sql = "INSERT INTO category (name) values (?)";
		DBManager manager = DBManager.getInstance();
		Connection con = manager.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, m.getName());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		allCategories.put(m.getId(), m);
	}

	public HashMap<Integer, Category> getAllCategories() throws SQLException {
		if (allCategories.isEmpty()) {
			String sql = "SELECT category_id, name from category;";
			DBManager manager = DBManager.getInstance();
			Connection con = manager.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Category m = new Category(res.getInt("category_id"), res.getString("name"));
				allCategories.put(m.getId(), m);
			}
		}
		return allCategories;
	}

	public synchronized boolean validateCategry(int id) throws SQLException {
		if (getAllCategories().containsKey(id)) {
			return true;
		}
		return false;
	}
}