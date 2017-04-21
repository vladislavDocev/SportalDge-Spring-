package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Post;

@Controller
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
		//if yes check if user is admin
			//if yes check if post exists in DB
				//if yes insert into DB, upload picture and insert and show in current page
				location = "";
				//else -> show "this post exist" to user
				location = "";
			//	
		//else -> show invalid post to user
		}
		m.addAttribute(p);
		return location;
	}
	
	@RequestMapping(value = "/editPost", method = RequestMethod.POST)
	public String editPost(Model m){
		//check if logged
			//if yes check if user is admin
				//if yes check if post exists in DB
					//if yes update into DB and show in current page
					//else -> show "post not exist" to user
				//
			//else -> show "you are not admin" to user
		//if no -> forward to login page
		return null;
	}
	
	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public String deletePost(Model m){
		//check if logged
			//if yes check if user is admin
				//if yes check if post exists in DB
					//if yes delete into DB and show in current page
					//else -> show "post not exist" to user
				//
			//else -> show "you are not admin" to user
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
