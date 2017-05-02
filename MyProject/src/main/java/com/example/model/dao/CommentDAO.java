package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;

import com.example.model.Comment;
import com.example.model.Like;
import com.example.model.Post;
import com.example.model.User;

public class CommentDAO {
	private static CommentDAO instance;
	private static final HashMap<Integer, Comment> ALL_COMMENTS = new HashMap<>();// comment
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
		String sql = "INSERT INTO comment (location_id, description, date, author_id) values (?,?,?,?)";
		DBManager manager = DBManager.getInstance();
		Connection con = manager.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		c.setDate(LocalDateTime.now().toString());
		st.setInt(1, c.getPost().getPostID());
		st.setString(2, c.getCommentDesc());
		st.setString(3, c.getDate());
		st.setInt(4, c.getUser().getId());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		int id = res.getInt(1);
		Comment comment = c;
		comment.setId(id);
		ALL_COMMENTS.put(c.getId(), comment);
	}

	public HashMap<Integer, Comment> getAllComments() throws SQLException {
		if (ALL_COMMENTS.isEmpty()) {
			String sql = "select comment_id,location_id,description,date,author_id from comment";
			DBManager manager = DBManager.getInstance();
			Connection con = manager.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			UserDAO dao = UserDAO.getInstance();
			PostDAO postDao = PostDAO.getInstance();
			HashMap<Integer, Post> posts = postDao.getAllPosts();
			HashMap<Integer, User> users = dao.getAllUsers();
			
			while (res.next()) {
				User u = users.get(res.getInt("author_id"));
				Post p = posts.get(res.getInt("location_id"));
				int commentId = res.getInt("comment_id");
				Comment c = new Comment(res.getString("description"), u, p, commentId, res.getString("date"));
				
				ALL_COMMENTS.put(c.getCommentID(), c);
			}
			
		}
		
		return ALL_COMMENTS;
	}

	public synchronized boolean validComment(int id) throws SQLException {
		if (getAllComments().containsKey(id)) {
			return true;
		}
		return false;
	}

	public void deleteComments(int postID) {
		String sql = "SELECT location_id where location_id ="+postID+ ";";
		DBManager manager = DBManager.getInstance();
		Connection con = manager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			sql = "DELETE FROM sportaldb.comment where location_id ="+postID+ ";";
			st = con.prepareStatement(sql);
			while (res.next()) {
				st.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	}
}
