package com.example.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Category;
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

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchPost(ModelMap model, @RequestParam String header, @RequestParam String category,
			HttpSession session) {
		String location = (String) session.getAttribute("location");
		User u = (User) session.getAttribute("user");
		model.addAttribute("user", u);

		if (header == null || header.equals("") || !POST_DAO.containsHeader(header)) {
			location = searchResults(model, session, category);
		}

		try {
			HashMap<Integer, Post> posts = POST_DAO.getAllPosts();
			Post p = new Post();
			p.setHeader(header);
			Category cat = null;
			HashMap<Integer, Category> categories = CATEGORY_DAO.getAllCategories();
			for (Entry<Integer, Category> entry : categories.entrySet()) {
				Category c = entry.getValue();
				if (c.getName().equals(category)) {
					cat = c;
					break;
				}
			}
			p.setCategory(cat);
			for (Entry<Integer, Post> entryset : posts.entrySet()) {
				Post post = entryset.getValue();
				if (p.getHeader().equals(post.getHeader())) {
					if (p.getCategory().equals(post.getCategory())) {
						p = post;
						location = UserController.viewPost(model, p.getPostID(), session);
						session.setAttribute("location", location);
						break;
					}
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
		if (session.isNew()) {
			session.setAttribute("user", user);
		}

		try {
			HashMap<Integer, Category> categories = CATEGORY_DAO.getAllCategories();
			HashMap<Integer, Post> posts = POST_DAO.getAllPosts();
			List<Post> ps = new ArrayList<>();
			for (Entry<Integer, Post> entry : posts.entrySet()) {
				Post post = entry.getValue();
				ps.add(post);
			}
			session.setAttribute("posts", ps);
			session.setAttribute("categories", categories);

			model.addAttribute("posts", ps);
			model.addAttribute("categories", categories);

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
					if (u.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
						user = u;
						if (u.isAdmin() != 0) {
							session.setAttribute("admin", user);
							session.setAttribute("user", user);
							session.setAttribute("logged", true);
							location = "adminHome";
							break;
						} else {
							session.setAttribute("user", user);
							session.setAttribute("logged", true);
							location = "home";
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
		System.out.println(session.getAttribute("user") + " in login");
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
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
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

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap model, HttpSession session) {
		String location = "index";
		User u = ((User) session.getAttribute("user"));
		model.addAttribute("user", u);
		if (session.isNew()) {
			session.setAttribute("user", new User());
		}
		if (u != null) {
			if (u.getName() != null) {
				if(u.isAdmin() != 0){
					location = "adminHome";
					
				}else{
					location = "home";
				}
			}
		} else {
			location = "index";
		}

		try {
			HashMap<Integer, Category> categories = CATEGORY_DAO.getAllCategories();
			HashMap<Integer, Post> posts = POST_DAO.getAllPosts();
			List<Post> viewed = new ArrayList<>();
			session.setAttribute("categories", categories);
			for (Entry<Integer, Post> entryset : posts.entrySet()) {
				Post p = entryset.getValue();
				viewed.add(p);
			}

			session.setAttribute("posts", viewed);
			model.addAttribute("posts", viewed);
			model.addAttribute("categories", categories);
		} catch (SQLException e) {
			// TODO
			// error page
			e.printStackTrace();
			location = "index";
		}

		System.out.println("location is " + location);
		return location;
	}

	@RequestMapping(value = "/mostViewed", method = RequestMethod.GET)
	public String mostViewed(ModelMap model, HttpSession session) {
		String location = "mostViewed";
		User u = ((User) session.getAttribute("user"));
		model.addAttribute("user", u);
		if (session.isNew()) {
			session.setAttribute("user", new User());
		}

		if (u != null) {
			if (u.getName() != null) {
				System.out.println("location is home");
				location = "mostViewedLogged";
			}
		} else {
			location = "mostViewed";
		}

		try {
			HashMap<Integer, Category> categories = CATEGORY_DAO.getAllCategories();
			HashMap<Integer, Post> posts = POST_DAO.getAllPosts();
			List<Post> viewed = new ArrayList<>();
			session.setAttribute("posts", posts);
			session.setAttribute("categories", categories);
			for (Entry<Integer, Post> entryset : posts.entrySet()) {
				Post p = entryset.getValue();
				viewed.add(p);
			}
			viewed.sort((a, b) -> {
				return b.getViews() - a.getViews();
			});
			model.addAttribute("posts", viewed);
			model.addAttribute("categories", categories);
			if (viewed.size() > 5) {
				List<Post> mostViewed = viewed.subList(0, 5);
				System.out.println(mostViewed);
				model.addAttribute("mostViewed", mostViewed);
				session.setAttribute("mostViewed", mostViewed);
			} else {
				model.addAttribute("mostViewed", viewed);
				session.setAttribute("mostViewed", viewed);
			}
		} catch (SQLException e) {
			// TODO
			// error page
			e.printStackTrace();
		}

		return location;
	}

	@RequestMapping(value = "/searchResults", method = RequestMethod.POST)
	public String searchResults(ModelMap model, HttpSession session, String category) {
		String location = "searchResults";
		User u = ((User) session.getAttribute("user"));
		model.addAttribute("user", u);
		if (session.isNew()) {
			session.setAttribute("user", new User());
		}

		if (u != null) {
			if (u.getName() != null) {
				if(u.isAdmin() == 0){
					location = "searchResultsLogged";
				}else{
					location = "searchResultsAdmin";
				}
			}
		} else {
			location = "searchResults";
		}

		try {

			HashMap<Integer, Category> categories = CATEGORY_DAO.getAllCategories();
			HashMap<Integer, Post> posts = POST_DAO.getAllPosts();
			HashMap<Integer, Post> categorized = new HashMap<>();
			List<Post> categ = new ArrayList<>();
			session.setAttribute("categories", categories);
			for (Entry<Integer, Post> entryset : posts.entrySet()) {
				Post p = entryset.getValue();
				if (p.getCategory().getName().equals(category)) {
					categ.add(p);
					categorized.put(p.getPostID(), p);
				}
			}
			model.addAttribute("posts", categ);
			model.addAttribute("categories", categories);
			session.setAttribute("posts", categ);
		} catch (SQLException e) {
			// TODO
			// error page
			e.printStackTrace();
		}
		return location;
	}
	
	
	@RequestMapping(value = "/makeCreatePost", method = RequestMethod.GET)
	public String makeCreatePost(ModelMap model, HttpSession session) {
		String location = "index";
		User u = ((User) session.getAttribute("admin"));
		model.addAttribute("user", u);
		if (session.isNew()) {
			session.setAttribute("admin", new User());
		}
		if (u != null) {
			if (u.getName() != null) {
				location = "createPost";
			}
		} else {
			location = "index";
		}

		
		return location;
	}
}
