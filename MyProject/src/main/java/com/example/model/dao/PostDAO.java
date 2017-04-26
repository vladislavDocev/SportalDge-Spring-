package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;

import com.example.model.Category;
import com.example.model.Media;
import com.example.model.Post;
import com.example.model.User;

public class PostDAO {
	private static PostDAO instance;
	private static final HashMap<Integer, Post> allPosts = new HashMap<>();// content
																			// ->
																			// Post

	private PostDAO() {

	}

	public static synchronized PostDAO getInstance() {
		if (instance == null) {
			instance = new PostDAO();
		}
		return instance;
	}

	public void addPost(Post p) throws SQLException {
		String sql = "INSERT INTO post (content, date, auth_id, header, cat_id, views) values (?, ?, ?, ?, ?, ?)";
		DBManager manager = DBManager.getInstance();
		Connection con = manager.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		p.setDate(LocalDateTime.now().toString());
		st.setString(1, p.getContent());
		st.setString(2, p.getDate());
		st.setString(3, p.getAutor().getName());
		st.setString(4, p.getHeader());
		st.setInt(5, p.getCategory().getId());
		st.setInt(6, 0);
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		int id = res.getInt(1);
		Post post = p;
		post.setId(id);
		allPosts.put(p.getPostID(), p);
	}

	public HashMap<Integer, Post> getAllPosts() throws SQLException {
		if (allPosts.isEmpty()) {
			String sql = "select post_id,header,content,date,views,auth_id,cat_id  from post";
			DBManager manager = DBManager.getInstance();
			Connection con = manager.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			CategoryDAO dao = CategoryDAO.getInstance();
			UserDAO uDao = UserDAO.getInstance();
			MediaDAO mDao = MediaDAO.getInstance();

			HashMap<Integer, User> users = uDao.getAllUsers();
			HashMap<Integer, Category> categories = dao.getAllCategories();
			HashMap<Integer, Media> media = mDao.getAllMedia();

			while (res.next()) {
				Category c = categories.get(res.getInt("cat_id"));
				User u = users.get(res.getInt("auth_id"));
				Post p = new Post(res.getString("content"), res.getString("header"), c, res.getInt("views"),
						res.getInt("post_id"), u);
				for (Entry<Integer, Media> entry : media.entrySet()) {
					Media m = entry.getValue();
					int test = m.getPost().getPostID();
					int postId = p.getPostID();
					if (test == postId) {
						p.addMedia(m);
					}
				}
				allPosts.put(p.getPostID(), p);
			}
		}
		return allPosts;
	}

	public synchronized boolean validCreatePost(int id) throws SQLException {
		if (getAllPosts().containsKey(id)) {
			return true;
		}
		return false;
	}

	public synchronized void updateViews(Post p) throws SQLException {
		String sql = "UPDATE post SET views = " + p.getViews() + " WHERE post_id = " + p.getPostID();
		DBManager manager = DBManager.getInstance();
		Connection con = manager.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.executeQuery();
	}
}
