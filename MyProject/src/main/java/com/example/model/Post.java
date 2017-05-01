package com.example.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Post {
	private int postID;
	private String content;
	private String header;
	private String date;
	private User author;
	private Category category;
	private int views;
	private HashSet<Comment> comments;

	public Post(String content, String header, Category category, int views, int postID, User u, String date) {
		this.content = content;
		this.header = header;
		this.postID = postID;
		this.views = views;
		this.category = category;
		this.author = u;
		this.date = date;
		this.comments = new HashSet<>();
	}

	public Post() {
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

	public Set<Comment> getComments() {
		Set<Comment> temp = new HashSet<>();
		if (this.comments != null) {
			 temp = Collections.unmodifiableSet(this.comments);
		}
		return temp;
	}

	public void addComment(Comment c) {
		this.comments.add(c);
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
