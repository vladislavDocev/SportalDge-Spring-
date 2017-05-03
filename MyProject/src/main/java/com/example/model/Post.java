package com.example.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Post {
	private int postID;
	private String content;
	private String header;
	private String date;
	private User author;
	private Category category;
	private int views;
	private HashMap<Integer, Comment> comments;

	public Post(String content, String header, Category category, int views, int postID, User u, String date) {
		this();
		this.content = content;
		this.header = header;
		this.postID = postID;
		this.views = views;
		this.category = category;
		this.author = u;
		this.date = date;
		
	}

	public Post() {
		this.comments = new HashMap<>();
	}

	public int getPostID() {
		return this.postID;
	}

	public String getContent() {
		return this.content;
	}

	public String getHeader() {
		return this.header;
	}

	public String getDate() {
		return this.date;
	}

	public User getAutor() {
		return this.author;
	}

	public Category getCategory() {
		return this.category;
	}

	public int getViews() {
		return this.views;
	}

	public String getAuthorUsername() {
		return this.author.getUsername();
	}

	public Map<Integer,Comment> getComments() {
		Map<Integer,Comment> temp = new HashMap<>();
		if (this.comments != null) {
			 temp = Collections.unmodifiableMap(this.comments);
		}
		return temp;
	}

	public void addComment(Comment c) {
		this.comments.put(c.getCommentID(), c);
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAuthor(User u) {
		this.author = u;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public void setId(int id) {
		this.postID = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	//
}
