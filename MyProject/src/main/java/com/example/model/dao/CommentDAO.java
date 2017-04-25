package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.example.model.Comment;
import com.example.model.User;

public class CommentDAO {
	private static CommentDAO instance;
	private static final HashMap<Integer, Comment> allComments = new HashMap<>();// comment id -> Comment
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
		st.setInt(1, c.getPost());
		st.setString(2, c.getDescription());
		st.setString(3, c.getDate());
		st.setInt(4, c.getLikes());
		st.setInt(5, c.getUser().getId());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
//		long id = res.getLong(1);
	}

	public HashMap<Integer, Comment> getAllComments() throws SQLException {
		if (allComments.isEmpty()) {
			String sql = "select c.comment_id, c.location_id,c.description,c.date,c.likes, u.name from comment c"+
						 "join user u on c.author_id = u.user_id";
			DBManager manager = DBManager.getInstance();
			Connection con = manager.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				UserDAO dao = UserDAO.getInstance();
				HashMap<String, User> users = dao.getAllUsers();
				User u = users.get(res.getString("u.name"));
				Comment c = new Comment(res.getString("c.description"), u, res.getInt("c.location_id"));
				c.setCommentID(res.getInt("c.comment_id"));
				c.setDate(res.getString("c.date"));
				c.setLikes(res.getInt("c.likes"));
				allComments.put(res.getInt("c.comment_id"), c);
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
