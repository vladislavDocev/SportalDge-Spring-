package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Post {

	private int postID;
	private String content;
	private String header;
	private String date;
	private User author;
	private Category category;
	private int views;
	private ArrayList<Media> pictures;
	private ArrayList<Comment> comments;

	public Post(String content, String header, Category category, int views, int postID, User u) {
		this.content = content;
		this.header = header;
		this.postID = postID;
		this.views = views;
		this.category = category;
		this.author = u;
		this.pictures = new ArrayList<>();
		this.comments = new ArrayList<>();
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

	public List<Media> getPictures() {
		List<Media> temp = Collections.unmodifiableList(this.pictures);
		return temp;
	}

	public List<Comment> getComments() {
		List<Comment> temp = Collections.unmodifiableList(this.comments);
		return temp;
	}
	public void addComment(Comment c) {
		this.comments.add(c);
	}

	public void addMedia(Media m) {
		this.pictures.add(m);
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAuthor(User u ) {
		this.author = u;
	}
	
	public void setViews(int views) {
		this.views = views;
	}

	public void setId(int id) {
		this.postID = id;
	}

}
