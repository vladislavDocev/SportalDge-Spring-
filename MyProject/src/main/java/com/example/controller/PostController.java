package com.example.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Category;
import com.example.model.Comment;
import com.example.model.Like;
import com.example.model.Post;
import com.example.model.User;
import com.example.model.dao.DBManager;
import com.example.model.dao.PostDAO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.Connection;

@Controller
@SessionAttributes("user")
public class PostController {

	@RequestMapping(value = "/createPost", method = RequestMethod.POST)
	public String createPost(Model m) {
		Post post = new Post();
		m.addAttribute("post", post);
		return "admin";
	}

	@ResponseBody
	@RequestMapping(value = "/makePost", method = RequestMethod.POST)
	public void makePost(HttpServletRequest req, @ModelAttribute Post p, Model m, HttpSession s) {
		try {
			HashMap<Integer, Category> categories = MyController.CATEGORY_DAO.getAllCategories();
			m.addAttribute("categories" ,categories);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//error page
		}
		Scanner sc = null;
		try {
			sc = new Scanner(req.getInputStream());
		} catch (Exception e) {
			System.out.println("Greshka");
		}

		StringBuilder str = new StringBuilder();

		while (sc.hasNextLine()) {
			str.append(sc.nextLine());
		}

		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(str.toString()).getAsJsonObject();

		String content = obj.get("content").getAsString();
		String header = obj.get("header").getAsString();
		String category = obj.get("category").getAsString();
		if (s.isNew() || s.getAttribute("admin") == null) {

		} else {
			// if yes check if post exists in DB
			try {
				if (!MyController.POST_DAO.validCreatePost(p.getPostID())) {
					// if no insert into DB, upload picture and insert and show
					// in current page
					User u = (User) s.getAttribute("admin");
					System.out.println(u);
					p.setDate(LocalDateTime.now().toString());
					p.setAuthor(u);
					p.setContent(content);
					p.setHeader(header);
					Category cat = MyController.CATEGORY_DAO.category(category);
					p.setCategory(cat);
					MyController.POST_DAO.addPost(p);
						
					
				} else {

				}
			} catch (SQLException e) {
				// show error page
				e.printStackTrace();

			}
			//
		}
		m.addAttribute(p);
	}

	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public String deletePost(Model m, HttpSession s) {
		// check if logged
		String location = "";
		if (s.isNew() || s.getAttribute("admin") == null) {
			// if yes -> forward to login page
		} else {
			DBManager manager = DBManager.getInstance();
			Connection con = (Connection) manager.getConnection();
			Post p = (Post) s.getAttribute("post");
			Map<Integer, Comment> comments = p.getComments();
			try {

				con.setAutoCommit(false);

				for (Entry<Integer, Comment> entryset : comments.entrySet()) {
					Comment c = entryset.getValue();
					MyController.LIKE_DAO.deleteLikes(c.getCommentID());
				}

				MyController.COMMENT_DAO.deleteComments(p.getPostID());
				MyController.POST_DAO.deletePost(p.getPostID());

			} catch (SQLException e) {
				if (con != null) {
					try {
						System.err.print("Transaction is being rolled back");
						con.rollback();
					} catch (SQLException excep) {
						excep.printStackTrace();
					}
				}
			} finally {

				try {
					con.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// if yes delete into DB and show in current page
			location = "";
			// else -> show "post not exist" to user
			location = "";
		}
		return location;
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String post() {
		return "post";
	}
}
