package com.example.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;
import com.example.model.dao.CategoryDAO;
import com.example.model.dao.CommentDAO;
import com.example.model.dao.LikeDAO;
import com.example.model.dao.PostDAO;
import com.example.model.dao.UserDAO;

@Controller
public class MyController {

	public static final UserDAO USER_DAO = UserDAO.getInstance();
	public static final CategoryDAO CATEGORY_DAO = CategoryDAO.getInstance();
	public static final CommentDAO COMMENT_DAO = CommentDAO.getInstance();
	public static final LikeDAO LIKE_DAO = LikeDAO.getInstance();
	public static final PostDAO POST_DAO = PostDAO.getInstance();

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchPost(Model model, @RequestParam String header, @RequestParam String category,
			HttpSession session) {
		String location = "index";
		try {
			boolean flag = false;
			HashMap<Integer, Post> posts = POST_DAO.getAllPosts();
			Post post = null;
			for (Entry<Integer, Post> entryset : posts.entrySet()) {
				Post p = entryset.getValue();
				if (p.getHeader().equals(header)) {
					if (p.getCategory().equals(category)) {
						post = p;
						model.addAttribute(post);
						flag = true;
						break;
					}
				}
				if (flag) {
					// load post
					if (post != null) {
						location = UserController.viewPost(model, post.getPostID(), session);
					}
				} else {
					// error page
					location = "";
				}
			}
		} catch (SQLException e) {
			// TODO
			// error page
			location = "";
		}
		return location;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String sayHi(ModelMap model, HttpSession session) {
		String location = "index";
		User user = new User();
		model.addAttribute("user", user);
		if(session.isNew()){
			session.setAttribute("user", user);
		}
		
		try {
			HashMap<Integer, Post> posts = POST_DAO.getAllPosts();
			List<Post> viewed = new ArrayList<>();
			session.setAttribute("posts", posts);
			for (Entry<Integer, Post> entryset : posts.entrySet()) {
				Post p = entryset.getValue();
				viewed.add(p);
			}
			viewed.sort((a, b) -> {
				return a.getViews() - b.getViews();
			});
			model.addAttribute("posts", posts);
			if (viewed.size() > 5) {
				List<Post> mostViewed = (ArrayList<Post>) viewed.subList(0, 4);
				viewed = null;
				model.addAttribute("mostViewed", mostViewed);
				session.setAttribute("mostViewed", mostViewed);
			}
		} catch (SQLException e) {
			// TODO
			// error page
			e.printStackTrace();
			location = "index";
		}

		return location;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, HttpSession session) {
		// check if username and password are in the DB
		String location = "index";
		try {
			HashMap<Integer, User> users = USER_DAO.getAllUsers();
			for (Entry<Integer, User> entries : users.entrySet()) {
				User u = entries.getValue();
				String test = u.getUsername();
				String username = user.getUsername();
				if (test.equals(username)) {
					if (u.getPassword().equals(user.getPassword())) {
						user = u;
						if (u.isAdmin() != 0) {
							session.setAttribute("admin", user);
							session.setAttribute("user", user);
							session.setAttribute("logged", true);
							location = "createPost";
							break;
						} else {
							session.setAttribute("user", user);
							session.setAttribute("logged", true);
							location = "index";
							break;
						}
					} else {
						session.setAttribute("logged", false);
						location = "loginFailed";
					}
				} else {
					session.setAttribute("logged", false);
					location = "loginFailed";
				}
			}

		} catch (SQLException e) {
			location = ""; // error page
		}
		return location;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPage(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "registerPage";
	}

	@RequestMapping(value = "/registered", method = RequestMethod.GET)
	public String loadUser(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "registerPage";
	}

	@RequestMapping(value = "/registered", method = RequestMethod.POST)
	public String loginPage(@ModelAttribute User user) {
		String location = "index";
		try {
			// check if username or email exist in DB
			HashMap<Integer, User> users = USER_DAO.getAllUsers();
			int id = user.getId();
			if (!users.containsKey(id)) {
				// if not register user and forward to index page
				USER_DAO.addUser(user);
			} else {
				// if yes return registerFailed page
				location = "registerFailed";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			location = "registerFailed"; // error page
		}

		return location;
	}

}
