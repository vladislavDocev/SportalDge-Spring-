package com.example.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Post;

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
				//if no insert into DB, upload picture and insert and show in current page
				p.setDate(LocalDateTime.now().toString());
				location = "";
				//else -> show "this post exist" to user
				location = "";
			//	
		}
		m.addAttribute(p);
		return location;
	}
	
	@RequestMapping(value = "/editPost", method = RequestMethod.POST)
	public String editPost(Model m, HttpSession s){
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
	public String deletePost(Model m){
		//check if logged
			//if yes check if post exists in DB
				//if yes delete into DB and show in current page
				//else -> show "post not exist" to user
			//
		//if no -> forward to login page
		return null;
	}
	
	@RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
	public String uploadPicture(Model m){
		return null;
	}
	
	@RequestMapping(value = "/deletePicture", method = RequestMethod.POST)
	public String deletePicture(Model m){
		return null;
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public String post() {
		return "post";
	}
}
