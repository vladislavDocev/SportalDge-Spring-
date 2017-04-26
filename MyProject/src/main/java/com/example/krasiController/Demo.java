//package com.example.krasiController;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import com.example.model.dao.DBManager;
//
//public class Demo {
//	public static void main(String[] args) {
//		String sql = "INSERT INTO user (username, password,email) values(?, ?, ?)";
//		DBManager manager = DBManager.getInstance();
//		Connection c = manager.getConnection();
//		try {
//			PreparedStatement st = c.prepareStatement(sql);
//			st.setString(1, "Gosho123");
//			st.setString(2, "Gosho123");
//			st.setString(3, "email@abv.bg");
//			st.executeUpdate();
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//	}
//}
