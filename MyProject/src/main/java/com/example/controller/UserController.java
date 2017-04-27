package com.example.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Comment;
import com.example.model.Media;
import com.example.model.Post;
import com.example.model.User;
import com.example.model.dao.CommentDAO;
import com.example.model.dao.MediaDAO;
import com.example.model.dao.PostDAO;

@Controller
@SessionAttributes("user")
public class UserController {

	@RequestMapping(value = "/createComment", method = RequestMethod.GET)
	public String createComment(Model model) {
		Comment comment = new Comment();
		model.addAttribute("comment", comment);
		return "post";
	}

	@RequestMapping(value = "/post/{postId}/comment", method = RequestMethod.POST)
	public String addComment(@PathVariable(value = "postId") int postId, HttpSession session, HttpServletRequest req,
			Model model) {
		if (session.getAttribute("user") != null) {
			try {
				PostDAO dao = PostDAO.getInstance();
				HashMap<Integer, Post> posts = dao.getAllPosts();
				if (posts.containsKey(postId)) {
					String comment = req.getParameter("comment");
					User u = (User) session.getAttribute("user");
					Post p = posts.get(postId);
					Comment c = new Comment(comment, u, p);
					CommentDAO commentDao = CommentDAO.getInstance();
					commentDao.addComment(c);
					return viewPost(model, postId);
				}
			} catch (NumberFormatException | SQLException e) {
				System.out.println("SQL" + e.getMessage());
				// TODO
				// error page
				return "";
			}
			return "index";
		}
		return "login";
	}

	@RequestMapping(value = "post/{postId}", method = RequestMethod.GET)
	public static String viewPost(Model model, @PathVariable(value = "postId") int postId) {
		try {
			PostDAO dao = PostDAO.getInstance();
			MediaDAO mDao = MediaDAO.getInstance();
			CommentDAO cDao = CommentDAO.getInstance();
			HashMap<Integer, Post> posts = dao.getAllPosts();
			HashMap<Integer, Media> media = mDao.getAllMedia();
			HashMap<Integer, Comment> comments = cDao.getAllComments();

			if (posts.containsKey(postId)) {
				Post post = posts.get(postId);
				post.setViews(post.getViews() + 1);
				dao.updateViews(post);
				for (Entry<Integer, Media> entry : media.entrySet()) {
					Media m = entry.getValue();
					int test = m.getPost().getPostID();
					if (test == postId) {
						post.addMedia(m);
					}
				}
				for (Entry<Integer, Comment> entry : comments.entrySet()) {
					Comment c = entry.getValue();
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
			return "index";
		}
	}
}
