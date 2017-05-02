package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import com.example.model.Category;

public class CategoryDAO {
	private static CategoryDAO instance;
	private static final HashMap<Integer, Category> ALL_CATEGORIES = new HashMap<>();

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
		ALL_CATEGORIES.put(m.getId(), m);
		
	}

	public HashMap<Integer, Category> getAllCategories() throws SQLException {
		if (ALL_CATEGORIES.isEmpty()) {
			String sql = "SELECT category_id, name from category;";
			DBManager manager = DBManager.getInstance();

			Connection con = manager.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Category m = new Category(res.getInt("category_id"), res.getString("name"));
				ALL_CATEGORIES.put(m.getId(), m);
			}
			
		}
		return ALL_CATEGORIES;
	}

	public synchronized boolean validateCategry(int id) throws SQLException {
		if (getAllCategories().containsKey(id)) {
			return true;
		}
		return false;
	}

	public Category category(String category) {
		String cat = "\""+category+"\"";
		String sql = "SELECT category_id from category where name = " + cat + ";";
		DBManager manager = DBManager.getInstance();
		Category c = new Category();
		Connection con = manager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			res.next();
			int id = res.getInt("category_id");
			c = ALL_CATEGORIES.get(id);
			
		} catch (SQLException e) {

		}
		return c;
	}

	public boolean containsCategory(String category) {
		boolean flag = false;
		for (Entry<Integer,Category> entryset : ALL_CATEGORIES.entrySet()) {
			Category c = entryset.getValue();
			if(c.getName().equals(category)){
				flag = true;
				break;
			}
		}
		return flag;
	}
}