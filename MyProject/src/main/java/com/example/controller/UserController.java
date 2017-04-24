package com.example.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Comment;
import com.example.model.User;
import com.example.model.dao.CommentDAO;

@Controller
@SessionAttributes("user")
public class UserController {

	
	@RequestMapping(value="/createComment", method=RequestMethod.GET)
	public String createComment(Model model) {
		Comment comment = new Comment();
		model.addAttribute("comment", comment);
		return "post";
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public  String comment(Model model, HttpSession s) {
		String location = "post";
		if(!s.isNew()) {
			//get info from front end
			try {
				//make comment
				
				Comment c = null;
				CommentDAO.getInstance().addComment(c);
			} catch (SQLException e) {
				//show error page
				location = "";
			}
			location = "post";
		}else{
			location = "index";
		}
		return location;
	}
	
	@RequestMapping(value="/like", method=RequestMethod.POST)
	public  String like(Model model, HttpSession s) {
		String location = "post";
		if(!s.isNew()) {
			try {
				//like comment
				//get comment from front end
				Comment c = new Comment();
				c.like();
				CommentDAO.getInstance().addComment(c);
			} catch (SQLException e) {
				//show error page
				location = "";
			}
			location = "post";
		}else{
			location = "index";
		}
		return location;
	}
	
}
