package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewsController {
	
	@RequestMapping(value = "/makePost", method = RequestMethod.POST)
	public String makePost(){
		return null;
	}
	
	@RequestMapping(value = "/editPost", method = RequestMethod.POST)
	public String editPost(){
		return null;
	}
	
	@RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
	public String uploadPicture(){
		return null;
	}
	
	@RequestMapping(value = "/deletePicture", method = RequestMethod.POST)
	public String deletePicture(){
		return null;
	}
	
	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public String deletePost(){
		return null;
	}
}
