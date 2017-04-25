package com.example.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Post;
import com.example.model.User;
import com.example.model.dao.PostDAO;

@Controller
@SessionAttributes("user")
public class PostController {
	
	@RequestMapping(value = "/createPost", method = RequestMethod.POST)
	public String createPost(Model m){
		Post post = new Post();
		m.addAttribute("post", post);
		return "admin";
	}
	
	@RequestMapping(value = "/makePost", method = RequestMethod.POST)
	public String makePost(@ModelAttribute Post p, Model m, HttpSession s){
		//check if logged
		String location = "";
		if(s.isNew()){
			//if no -> forward to login page
			location = "index";
		}	
		else{
			//if yes check if post exists in DB
				try {
					if(!PostDAO.getInstance().validCreatePost(p.getContent())){
						//if no insert into DB, upload picture and insert and show in current page
						User u = (User) s.getAttribute("user");
						String name = u.getName();
						p.setDate(LocalDateTime.now().toString());
						p.setAuthor(name);
						PostDAO.getInstance().addPost(p);
						location = "";
					}
					else{
						//else -> show "this post exist" to user
						location = "";
					}
				} catch (SQLException e) {
					//show error page
					location = "";
				}
			//	
		}
		m.addAttribute(p);
		return location;
	}
	
	@RequestMapping(value = "/editPost", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Post p, Model m, HttpSession s){
		//check if logged
		String location = "";
		if(s.isNew()){
			//if yes redirect to login page
			location = "index";
		}
		else{
			//check if post exists in DB
			//if yes update into DB and show in current page
			location = "";
			//else -> show "post not exist" to user
			location = "";
		}
		return location;
	}
	
	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public String deletePost(Model m, HttpSession s){
		//check if logged
		String location = "";
		if(s.isNew()){
			//if yes -> forward to login page
		}
		else{
			//if no check if post exists in DB
				//if yes delete into DB and show in current page
				location = "";
			//else -> show "post not exist" to user
			location = "";
		}
		return location;
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public String post() {
		return "post";
	}
}
