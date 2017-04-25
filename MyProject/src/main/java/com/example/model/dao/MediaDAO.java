package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.example.model.Media;


public class MediaDAO {
	private static MediaDAO instance;
	private static final HashMap<Integer, Media> allMedia= new HashMap<>();// id -> Media
	private MediaDAO() {

	}

	public static synchronized MediaDAO getInstance() {
		if (instance == null) {
			instance = new MediaDAO();
		}
		return instance;
	}

	public void addMedia(Media m) throws SQLException {
		String sql = "INSERT INTO media (media_link, post_id) values (?, ?)";
		DBManager manager = DBManager.getInstance();
		Connection con = manager.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, m.getMediaLink());
		st.setInt(2, m.getPost());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
//		long id = res.getLong(1);
		allMedia.put(m.getMediaID(), m);
	}

	public HashMap<Integer, Media> getAllMedia() throws SQLException {
		if (allMedia.isEmpty()) {
			String sql = "SELECT media_id,media_link, post_id from media;";
			DBManager manager = DBManager.getInstance();
			Connection con = manager.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Media m = new Media(res.getInt("media_id"), res.getString("media_link"), res.getInt("post_id"));
				allMedia.put(m.getMediaID(), m);
			}
		}
		return allMedia;
	}

	public synchronized boolean validateMedia(int id) throws SQLException {
		if (getAllMedia().containsKey(id)) {
			return true;
		}
		return false;
	}
}
