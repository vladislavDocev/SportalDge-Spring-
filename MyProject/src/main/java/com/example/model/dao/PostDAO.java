package com.example.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.example.model.Post;
import com.example.model.User;

public class PostDAO {
	private static PostDAO instance;
	private static final HashMap<String, Post> allPosts= new HashMap<>();// content -> Post
	private PostDAO() {

	}

	public static synchronized PostDAO getInstance() {
		if (instance == null) {
			instance = new PostDAO();
		}
		return instance;
	}

	public void addUser(Post p) throws SQLException {
		String sql = "INSERT INTO post (content, date, author_id, header) values (?, ?, ?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, p.getContent());
		st.setString(2, p.getDate());
		st.setInt(3, p.getAutor());
		st.setString(4, p.getHeader());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
//		long id = res.getLong(1);
		allPosts.put(p.getContent(), p);
	}

	public HashMap<String, Post> getAllPosts() throws SQLException {
		if (allPosts.isEmpty()) {
			String sql = "SELECT content, header, FROM user;";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Post p = new Post(res.getString("content"), res.getString("header"), res.getInt("user_id"));
						
				allPosts.put(p.getContent(), p);
			}
		}
		return allPosts;
	}

	public synchronized boolean validCreatePost(String content) throws SQLException {
		if (getAllPosts().containsKey(content)) {
			return true;
		}
		return false;
	}
}
