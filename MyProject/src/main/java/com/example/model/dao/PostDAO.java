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

	public void addPost(Post p) throws SQLException {
		String sql = "INSERT INTO post (content, date, author_id, header, category) values (?, ?, ?, ?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, p.getContent());
		st.setString(2, p.getDate());
		st.setString(3, p.getAutor());
		st.setString(4, p.getHeader());
		st.setString(5, p.getCategory());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
//		long id = res.getLong(1);
		allPosts.put(p.getContent(), p);
	}

	public HashMap<String, Post> getAllPosts() throws SQLException {
		if (allPosts.isEmpty()) {
			String sql = "select p.header, p.date, p.content, a.name as author_name, c.name as category_name from post p"+
						 "inner join user a on p.author_id = a.user_id"+
						 "inner join category c on p.category_id = c.category_id";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Post p = new Post(res.getString("p.content"), res.getString("p.header"), res.getString("c.name") );
				p.setDate(res.getString("p.date"));
				p.setAuthor(res.getString("a.name"));
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
