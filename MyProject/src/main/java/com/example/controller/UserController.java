package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class UserController {

	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public @ResponseBody String comment(Model model, HttpSession s) {
		String location = "post";
		if(!s.isNew()) {
			location = "post";
		}else{
			location = "index";
		}
		return location;
	}
}
