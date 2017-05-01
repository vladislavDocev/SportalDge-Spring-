package com.example.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Comment {

	private int commentID;
	private String comment;
	private String date;
	private User user;
	private Post post;
	private HashSet<Integer> likes;

	public Comment(String comment, User user, Post post) {
		this.comment = comment;
		this.user = user;
		this.post = post;
	}

	public Comment(String comment, User user, Post post, int commentId, String date) {
		this(comment, user, post);
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
		return comment;
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

	public void setUser(User user){
		this.user = user;
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
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
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
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
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
		if (this.likes == null || this.likes.size() == 0) {
			return 0;
		}
		return this.likes.size();
	}

	public Set<Integer> getLikers() {
		return Collections.unmodifiableSet((Set<Integer>) this.likes);
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
