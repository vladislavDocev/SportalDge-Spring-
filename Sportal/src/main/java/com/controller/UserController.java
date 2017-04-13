package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.User;

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
		//if yes return the main page
		s.setAttribute("user", u);
		//if no -> show invalid login to user 
		m.addAttribute(u);
		return "";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String createUser(Model m) {
		m.addAttribute("user", new User());
		return "";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute User u, Model m) {
		//check if username / email is available 
		//if yes save user to DB
		//return the main page and log in
		//if no -> show invalid register to user 
		m.addAttribute(u);
		return "";
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public String makeComment(Model m) {
		//check if logged 
		//if yes -> make comment in the post
		//save comment to DB
		//if no -> forward to login page
		return "";
	}
	
	@RequestMapping(value="/like", method=RequestMethod.POST)
	public String like(Model m) {
		//check if logged 
		//if yes -> make like in the post
		//increment likes on this post in DB
		//if no -> forward to login page
		return "";
	}
	
}
