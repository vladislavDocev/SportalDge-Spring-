package com.example.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Post;
import com.example.model.User;
import com.example.model.dao.PostDAO;
import com.example.model.dao.UserDAO;

@Controller
@SessionAttributes("user")
public class MyController {

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchPost(Model model, @RequestParam String header, @RequestParam String category) {
		String location = "index";
		PostDAO dao = PostDAO.getInstance();
		try {
			HashMap<String, Post> posts = dao.getAllPosts();
			if (posts.containsKey(header)) {
				Post p = posts.get(header);
				if (p.getCategory().equals(category)) {
					model.addAttribute("post", p);
					location = "post";
				} else {
					location = "index";
				}
			} else {
				// no results found
				location = "index";
			}
		} catch (SQLException e) {
			// TODO
			// error page
			location = "";
		}
		return location;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String sayHi(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, HttpSession session) {
		// check if username and password are in the DB
		String location = "index";
		try {
			HashMap<String, User> users = UserDAO.getInstance().getAllUsers();
			String username = user.getUsername();
			if (users.containsKey(username)) {
				User u = users.get(username);
				if (u.isAdmin() != 0) {
					session.setAttribute("admin", u);
					location = "admin";
				} else {
					// if yes forward to main page and add user to Session
					session.setAttribute("user", user);
					location = "index";
				}
			} else {
				// if no show loginFailed page
				location = "loginFailed";
			}
		} catch (SQLException e) {
			location = ""; // error page
		}
		return location;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPage() {
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
			UserDAO dao = UserDAO.getInstance();
			HashMap<String, User> users = dao.getAllUsers();
			String username = user.getUsername();
			if (!users.containsKey(username)) {
				// if not register user and forward to index page
				dao.addUser(user);
			} else {
				// if yes return registerFailed page
				location = "registerFailed";
			}
		} catch (SQLException e) {
			location = ""; // error page
		}

		return location;
	}

}
