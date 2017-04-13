package com.model;

import java.time.LocalDateTime;
import com.model.User;

public class Comment {
	
	private static int UNIQUE_ID = 1;
	private int commentID;
	private String description;
	private LocalDateTime date;
	private int likes;
	private User user;
	private Post post;
	
	public Comment(String description, User user, Post post) {
		this.description = description;
		this.user = user;
		this.post = post;
		this.commentID = UNIQUE_ID++;
		this.date = LocalDateTime.now();
	}

	public int getCommentID() {
		return commentID;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public int getLikes() {
		return likes;
	}

	public User getUser() {
		return user;
	}

	public Post getPost() {
		return post;
	}
	
}
