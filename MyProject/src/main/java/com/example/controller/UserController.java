package com.example.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;
import com.example.model.dao.CommentDAO;
import com.example.model.dao.PostDAO;

@Controller
@SessionAttributes("user")
public class UserController {

	
	@RequestMapping(value="/createComment", method=RequestMethod.GET)
	public String createComment(Model model) {
		Comment comment = new Comment();
		model.addAttribute("comment", comment);
		return "post";
	}	
	
	@RequestMapping(value = "/post/{postId}/comment", method = RequestMethod.POST)
	public String addComment(@PathVariable(value="postId") String postId, HttpSession session, HttpServletRequest req, Model model){
		if(session.getAttribute("user") != null){
			try {
				if(PostDAO.getInstance().getAllPosts().containsKey(postId)) {
					String comment = req.getParameter("comment");
					User u = (User) session.getAttribute("user");
					Post p = PostDAO.getInstance().getAllPosts().get(postId);
					Comment c = new Comment(comment, u, p.getPostID());
					CommentDAO.getInstance().addComment(c);
					return viewPost(model, postId, session);
					}
			} catch (NumberFormatException | SQLException e) {
				System.out.println("SQL" + e.getMessage());
				//TODO
				//error page
				return "";
			}
			return "index";
			}
		return "login";
		}
	
	
	@RequestMapping(value="post/{postId}",method = RequestMethod.GET)
	public static String viewPost (Model model, @PathVariable(value="postId") String postId, HttpSession session) {
		try {
			PostDAO dao = PostDAO.getInstance();
			HashMap<String, Post> posts = dao.getAllPosts();
			if(posts.containsKey(postId)) {
				Post  p = posts.get(postId);
				p.setViews(p.getViews() + 1);
				dao.updateViews(p);
				model.addAttribute("post",p);
				session.setAttribute("post", p);
				return "post";
			} 
			else {
				return "index";
			}				
		} 
		catch (SQLException e) {
			//TODO
			//error page
			return "";
		}			
	}
}
