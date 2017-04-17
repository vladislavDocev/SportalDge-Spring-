package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import com.model.Post;

public class PostDAO {
	private static PostDAO instance;
	private static final HashMap<LocalDate, Post> allPosts = new HashMap<>();// author -> user
	private PostDAO() {

	}

	public static synchronized PostDAO getInstance() {
		if (instance == null) {
			instance = new PostDAO();
		}
		return instance;
	}
	
	public void addPost(Post p) throws SQLException {
		//TODO insert
		String sql = "INSERT INTO Post";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, p.getContent());
		st.setString(2, p.getAutor());
		st.setString(3, p.getCategory());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		long id = res.getLong(1);
		allPosts.put(LocalDate.now(), p);
	}
	
	public void editPost(Post p)throws SQLException {
		//TODO update
	}
	
	public void deletePost(Post p)throws SQLException { 
		//TODO delete
	}
}
