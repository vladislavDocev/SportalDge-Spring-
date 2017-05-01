package com.example.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public void like(HttpServletRequest req, Model m, @ModelAttribute Like l, HttpSession session) {
		Scanner sc = null;
		try {
			sc = new Scanner(req.getInputStream());
		} catch (Exception e) {
			System.out.println("Greshka");
		}

		StringBuilder str = new StringBuilder();

		while (sc.hasNextLine()) {
			str.append(sc.nextLine());
		}

		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(str.toString()).getAsJsonObject();

		int id = obj.get("commentID").getAsInt();
		Post post = (Post) session.getAttribute("post");
		Map<Integer, Comment> comments = post.getComments();
		try {
			if (comments.containsKey(id)) {
				User u = ((User) session.getAttribute("user"));
				if (session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")) {
					Comment c = comments.get(id);
					l = new Like(u, c);
					MyController.LIKE_DAO.addLike(l);
				}
			}
		} catch (SQLException e) {
			// show error page
			e.printStackTrace();
		}
		//

		m.addAttribute(l);
	}

	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public void comment(HttpServletRequest req, @ModelAttribute Comment c, Model m, HttpSession session) {
		Scanner sc = null;
		try {
			sc = new Scanner(req.getInputStream());
		} catch (Exception e) {
			System.out.println("Greshka");
		}

		StringBuilder str = new StringBuilder();

		while (sc.hasNextLine()) {
			str.append(sc.nextLine());
		}

		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(str.toString()).getAsJsonObject();

		String commentDesc = obj.get("commentDesc").getAsString();
		Post post = (Post) session.getAttribute("post");
		try {
			if (!MyController.COMMENT_DAO.validComment(c.getCommentID())) {

				User u = ((User) session.getAttribute("user"));
				if (session.getAttribute("logged") != null && (Boolean) session.getAttribute("logged")) {
					c.setDate(LocalDateTime.now().toString());
					c.setUser(u);
					c.setCommentDesc(commentDesc);
					c.setPost(post);
					MyController.COMMENT_DAO.addComment(c);
				}
			}
		} catch (SQLException e) {
			// show error page
			e.printStackTrace();
		}
		//

		m.addAttribute(c);
	}

	@RequestMapping(value = "post/{postId}", method = RequestMethod.GET)
	public static String viewPost(Model model, @PathVariable(value = "postId") int postId, HttpSession session) {
		try {

			HashMap<Integer, Post> posts = MyController.POST_DAO.getAllPosts();
			HashMap<Integer, Comment> allComments = MyController.COMMENT_DAO.getAllComments();
			HashMap<Integer, Like> likes = MyController.LIKE_DAO.getAllLikes();
			if (posts.containsKey(postId)) {
				Post post = posts.get(postId);
				session.setAttribute("post", post);
				post.setViews(post.getViews() + 1);
				MyController.POST_DAO.updateViews(post);

				for (Entry<Integer, Comment> entry : allComments.entrySet()) {
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
				session.setAttribute("post", post);
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
}