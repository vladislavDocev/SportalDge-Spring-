package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import com.model.Comment;

public class CommentDAO {
	private static CommentDAO instance;
	private static final HashMap<LocalDate, Comment> allComments = new HashMap<>();// author -> user
	private CommentDAO() {

	}

	public static synchronized CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}

	public void addComment(Comment c) throws SQLException {
		//TODO insert
		String sql = "INSERT INTO comment";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, c.getDescription());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		long id = res.getLong(1);
		allComments.put(LocalDate.now(), c);
	}

	public void likeComment(Comment c) {
		String sql = "UPDATE INTO comment";
		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		allComments.get(c.getDate()).like();
	}
}
