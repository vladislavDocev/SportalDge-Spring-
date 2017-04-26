package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;

public class CommentDAO {
	private static CommentDAO instance;
	private static final HashMap<Integer, Comment> allComments = new HashMap<>();// comment
																					// id
																					// ->
																					// Comment

	private CommentDAO() {

	}

	public static synchronized CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}

	public void addComment(Comment c) throws SQLException {
		String sql = "INSERT INTO comment (location_id, description, date, likes, author_id) values (?,?,?,?,?)";
		DBManager manager = DBManager.getInstance();
		Connection con = manager.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		c.setDate(LocalDateTime.now().toString());
		st.setInt(1, c.getPost().getPostID());
		st.setString(2, c.getDescription());
		st.setString(3, c.getDate());
		st.setInt(4, c.getLikes());
		st.setInt(5, c.getUser().getId());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		int id = res.getInt(1);
		Comment comment = c;
		comment.setId(id);
		allComments.put(c.getId(), comment);
	}

	public HashMap<Integer, Comment> getAllComments() throws SQLException {
		if (allComments.isEmpty()) {
			String sql = "select comment_id,location_id,description,date,likes,author_id from comment";
			DBManager manager = DBManager.getInstance();
			Connection con = manager.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				UserDAO dao = UserDAO.getInstance();
				PostDAO postDao = PostDAO.getInstance();
				HashMap<Integer, Post> posts = postDao.getAllPosts();
				HashMap<Integer, User> users = dao.getAllUsers();
				User u = users.get(res.getInt("author_id"));
				Post p = posts.get(res.getInt("location_id"));
				Comment c = new Comment(res.getString("description"), u, p, res.getInt("comment_id"),
						res.getInt("likes"), res.getString("date"));
				allComments.put(res.getInt("comment_id"), c);
			}
		}
		return allComments;
	}

	public synchronized boolean validComment(int id) throws SQLException {
		if (getAllComments().containsKey(id)) {
			return true;
		}
		return false;
	}
}
