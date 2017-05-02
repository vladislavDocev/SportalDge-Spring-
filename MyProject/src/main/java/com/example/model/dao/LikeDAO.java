package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.example.model.Comment;
import com.example.model.Like;
import com.example.model.User;


public class LikeDAO {
	private static LikeDAO instance;
	private static final HashMap<Integer, Like> ALL_LIKES = new HashMap<>();
	private LikeDAO() {

	}

	public static synchronized LikeDAO getInstance() {
		if (instance == null) {
			instance = new LikeDAO();
		}
		return instance;
	}

	public void addLike(Like l) throws SQLException {
		String sql = "INSERT INTO sportaldb.like (liker_id, liked_comment_id) values (?,?)";
		DBManager manager = DBManager.getInstance();
		Connection con = manager.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, l.getUser());
		st.setInt(2, l.getComment());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		ALL_LIKES.put(l.getId(),l);
		try{}
		finally{
			
			st.close();
			res.close();
		}
	}

	public HashMap<Integer, Like> getAllLikes() throws SQLException {
		if (ALL_LIKES.isEmpty()) {
			String sql = "SELECT like_id, liker_id, liked_comment_id FROM sportaldb.`like`;";
			DBManager manager = DBManager.getInstance();
			Connection con = manager.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			UserDAO uDao = UserDAO.getInstance();
			HashMap<Integer, User> users = uDao.getAllUsers();
			
			CommentDAO cDao = CommentDAO.getInstance();
			HashMap<Integer, Comment> comments = cDao.getAllComments();
			
			while (res.next()) {
				User u = users.get(res.getInt("liker_id"));
				Comment c = comments.get(res.getInt("liked_comment_id"));
				
				Like l = new Like(res.getInt("like_id"), u , c);
				
				ALL_LIKES.put(l.getId(), l);
			}
			try{}
			finally{
				
				st.close();
				res.close();
			}
		}
		return ALL_LIKES;
	}

	public synchronized boolean validateLikes(int id) throws SQLException {
		if (getAllLikes().containsKey(id)) {
			return true;
		}
		return false;
	}

	public void deleteLikes(int commentID) {
		String sql = "SELECT liked_comment_id where liked_comment_id ="+commentID+ ";";
		DBManager manager = DBManager.getInstance();
		Connection con = manager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			sql = "DELETE FROM sportaldb.like where liked_comment_id ="+commentID+ ";";
			st = con.prepareStatement(sql);
			while (res.next()) {
				st.executeUpdate();
			}
			try{}
			finally{
				st.close();
				res.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	}
}