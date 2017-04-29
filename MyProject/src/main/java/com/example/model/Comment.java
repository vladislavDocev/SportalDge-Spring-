package com.example.model;

import java.util.HashSet;

public class Comment {

	private int commentID;
	private String description;
	private String date;
	private User user;
	private Post post;
	private HashSet<Integer> likes;
	
	public Comment(String description, User user, Post post) {
		this.description = description;
		this.user = user;
		this.post = post;
	}

	public Comment(String description, User user, Post post, int commentId, String date) {
		this(description, user, post);
		this.commentID = commentId;
		this.date = date;
		this.likes = new HashSet<>();
	}
	
	public Comment() {
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	
	public int getCommentID() {
		return commentID;
	}

	public String getDescription() {
		return description;
	}

	public String getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}

	public Post getPost() {
		return post;
	}

	public String getUsername() {
		return this.user.getUsername();
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void addLike(Like l) {
		this.likes.add(l.getUser());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentID;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (commentID != other.commentID)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public int getId() {
		return this.commentID;
	}

	public void setId(int id) {
		this.commentID = id;
	}
	
	public int getLikes() {
		return this.likes.size();
	}
	
}
