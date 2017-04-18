package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.krasiModel.User;

@Controller
public class MyController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String sayHi(Model viewModel) {
		
		return "index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute User u, Model viewModel) {
		
		return "index";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPage() {
		
		return "registerPage";
	}
	
	@RequestMapping(value="/registered", method=RequestMethod.POST)
	public String loginPage() {
		
		return "index";
	}
	
}
