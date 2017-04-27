//package com.example.krasiController;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//
//import com.example.model.Post;
//import com.example.model.dao.DBManager;
//import com.example.model.dao.PostDAO;
//
//public class Demo {
//	public static void main(String[] args) {
//		String sql = "SELECT media_id,media_link, p_id from media;";
//		DBManager manager = DBManager.getInstance();
//		PostDAO dao = PostDAO.getInstance();
//		
//		
//		Connection con = manager.getConnection();
//		PreparedStatement st;
//		try {
//			HashMap<Integer, Post> posts = dao.getAllPosts();
//			st = con.prepareStatement(sql);
//			ResultSet res = st.executeQuery();
//			int postId = res.getInt("media_id"); 
//			System.out.println(postId);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
