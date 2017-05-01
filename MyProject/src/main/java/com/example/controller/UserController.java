package com.example.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Comment;
import com.example.model.Like;
import com.example.model.Post;
import com.example.model.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class UserController {

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String createComment(Model model) {
		Comment comment = new Comment();
		model.addAttribute("comment", comment);
		return "post";
	}

	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public void comment(HttpServletRequest req,@ModelAttribute Comment c, Model m, HttpSession session) {
		Scanner sc = null;
		try{
			sc = new Scanner(req.getInputStream());
		}
		catch (Exception e) {
			System.out.println("Greshka");
		}
		
		StringBuilder str = new StringBuilder();
		
		while(sc.hasNextLine()){
			str.append(sc.nextLine());
		}
		
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(str.toString()).getAsJsonObject();
		
		String comment = obj.get("comment").getAsString();
		Post post =(Post) session.getAttribute("post");
		System.out.println(post);
		try {
					if(!MyController.COMMENT_DAO.validComment(c.getCommentID())){

						User u = ((User)session.getAttribute("user"));
						c.setDate(LocalDateTime.now().toString());
						c.setUser(u);
						c.setComment(comment);
						c.setPost(post);
						MyController.COMMENT_DAO.addComment(c);
					}
				} catch (SQLException e) {
					//show error page
					e.printStackTrace();
				}
			//	
		
		m.addAttribute(c);
	}

	@RequestMapping(value = "post/{postId}", method = RequestMethod.GET)
	public static String viewPost(Model model, @PathVariable(value = "postId") int postId, HttpSession session) {
		try {
			
			HashMap<Integer, Post> posts = MyController.POST_DAO.getAllPosts();
			HashMap<Integer, Comment> comments = MyController.COMMENT_DAO.getAllComments();
			HashMap<Integer, Like> likes = MyController.LIKE_DAO.getAllLikes();
			
			if (posts.containsKey(postId)) {
				Post post = posts.get(postId);
				session.setAttribute("post", post);
				post.setViews(post.getViews() + 1);
				MyController.POST_DAO.updateViews(post);

				for (Entry<Integer, Comment> entry : comments.entrySet()) {
					Comment c = entry.getValue();

					for (Entry<Integer, Like> entryset : likes.entrySet()) {
						Like l = entryset.getValue();
						if (l.getComment() == c.getCommentID()) {
							c.addLike(l);
						}
					}
					int test = c.getPost().getPostID();
					if (test == postId) {
						post.addComment(c);
					}
				}
				model.addAttribute("post", post);
				return "post";
			} else {
				return "index";
			}
		} catch (SQLException e) {
			// TODO
			// error page
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping(value="/post/comment/likeComment", method=RequestMethod.POST)
	public String likeComment(Model model, HttpSession session, @RequestParam int commentId, @RequestParam int postId) {
		try {
		
		HashMap<Integer, Comment> comments = MyController.COMMENT_DAO.getAllComments();
		
		Comment comment = comments.get(commentId);
		
		HashMap<Integer, User> users = MyController.USER_DAO.getAllUsers();
		
		int id = ((User) session.getAttribute("user")).getId();
		if (users.containsKey(id)){
			User user = users.get(id);
			if (!comment.getLikers().contains(user.getId())){
				Like l = new Like(user,comment);
				comment.addLike(l);
				MyController.LIKE_DAO.addLike(l);
				} 
			}else{
				return viewPost(model, postId,session);
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//error page
		}
		return viewPost(model, postId,session);
	}
}
