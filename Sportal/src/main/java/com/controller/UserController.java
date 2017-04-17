package com.controller;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.Comment;
import com.model.User;
import com.model.dao.CommentDAO;
import com.model.dao.UserDAO;

@Controller
public class UserController {
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String createUserLogin(Model m) {
		m.addAttribute("user", new User());
		return "";
	}
		
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@ModelAttribute User u, Model m, HttpSession s) {
		//check if user exists in DB
		String location = "";
		try {
			HashMap<String,User> users = UserDAO.getInstance().getAllUsers();
			if(users.containsKey(u.getUserName())) {
				//if yes return the main page
				s.setAttribute("user", u);
				location = "";
			}else{
				//if no -> show invalid login to user 
				location = "";
			}
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
		}
		m.addAttribute(u);
		return location;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String createUser(Model m) {
		m.addAttribute("user", new User());
		return "";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute User u, Model m,HttpSession s) {
		//check if username / email is available 
		String location = "";
		try {
			HashMap<String,User> users = UserDAO.getInstance().getAllUsers();
			if(users.containsKey(u.getUserName())) {
				//if yes -> show invalid register to user 
				location = "";
			}else{
				//if no save user to DB
				UserDAO.getInstance().addUser(u);
				//return the main page and log in
				s.setAttribute("user", u);
				location = "";
			}
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
		}
		m.addAttribute(u);
		return location;
	}

	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public String createComment(Model m) {
		m.addAttribute("comment", new Comment());
		return "";
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public String makeComment(@ModelAttribute Comment c ,Model m, HttpSession s) {
		//check if logged 
		String location = "";
		if(s.isNew()) {
			//if no -> forward to login page
			location = "";
		}else{
			//if yes -> make comment in the post
			
			try {
				//save comment to DB
				CommentDAO.getInstance().addComment(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return location;
	}
	
	@RequestMapping(value="/like", method=RequestMethod.POST)
	public String like(@ModelAttribute Comment c ,Model m, HttpSession s) {
		
		
		
		
		String location = "";
		//check if logged 
		if(s.isNew()) {
			//if no -> forward to login page
			location = "";
		}else{
			//if yes -> make like in the post
			c.like();
			m.addAttribute(c);
			//increment likes on this post in DB
			CommentDAO.getInstance().likeComment(c);
		}
	
		return location;
	}
	
}
