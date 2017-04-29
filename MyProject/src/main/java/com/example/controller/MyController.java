package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
			boolean flag = false;
			HashMap<Integer, Post> posts = dao.getAllPosts();
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
						location = UserController.viewPost(model, post.getPostID());
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
	public String sayHi(ModelMap model) {
		String location = "index";
		User user = new User();
		model.addAttribute("user", user);
		PostDAO dao = PostDAO.getInstance();
		
		try {
			HashMap<Integer, Post> posts = dao.getAllPosts();
			List<Post> viewed = new ArrayList<>();
			
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
			HashMap<Integer, User> users = UserDAO.getInstance().getAllUsers();
			for (Entry<Integer, User> entries : users.entrySet()) {
				User u = entries.getValue();
				String test = u.getUsername();
				System.out.println(test);
				String username = user.getUsername();
				if (test.equals(username)) {
					if (u.getPassword().equals(user.getPassword())) {
						user = u;
						if (u.isAdmin() != 0) {
							session.setAttribute("admin", user);
							location = "admin";
							break;
						} else {
							session.setAttribute("user", user);
							location = "index";
							break;
						}
					} else {
						location = "loginFailed";
					}
				} else {
					location = "loginFailed";
				}
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
			HashMap<Integer, User> users = dao.getAllUsers();
			int id = user.getId();
			if (!users.containsKey(id)) {
				// if not register user and forward to index page
				dao.addUser(user);
			} else {
				// if yes return registerFailed page
				location = "registerFailed";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			location = ""; // error page
		}

		return location;
	}

}
