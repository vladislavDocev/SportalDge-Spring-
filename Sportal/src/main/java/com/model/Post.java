package com.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

import com.model.User;

public class Post {
	
	public enum PostCategory{}
	
	private static int UNIQUE_ID = 1;
	private int postID;
	private String content;
	private LocalDateTime date;
	private User autor;
	private PostCategory category;
	private TreeSet<Media> pictures;
	private TreeSet<Comment> comments;
	
	public Post(String content, User autor, PostCategory category) {
		this.content = content;
		this.autor = autor;
		this.category = category;
		this.postID = UNIQUE_ID++;
		this.pictures = new TreeSet<>();
		this.comments = new TreeSet<>();
	}

	public Post() {}

	public int getPostID() {
		return postID;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getAutor() {
		return this.autor.getName();
	}

	public String getCategory() {
		return category.name();
	}

	public TreeSet<Media> getPictures() {
		TreeSet<Media> temp = (TreeSet<Media>) Collections.unmodifiableSet(this.pictures);
		return temp;
	}

	public TreeSet<Comment> getComments() {
		TreeSet<Comment> temp = (TreeSet<Comment>) Collections.unmodifiableSet(this.comments);
		return temp;
	}
	
	
	
}
