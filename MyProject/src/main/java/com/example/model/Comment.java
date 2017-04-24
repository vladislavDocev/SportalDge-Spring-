package com.example.model;


public class Comment {

	private int commentID;
	private String description;
	private String date;
	private int likes;
	private User user;
	private int post;
	private int dislikes;
	
	public Comment(String description, User user, int post) {
		this.description = description;
		this.user = user;
		this.post = post;
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

	public int getLikes() {
		return likes;
	}

	public User getUser() {
		return user;
	}

	public int getPost() {
		return post;
	}

	public void like() {
		this.likes++;
	}
	
	public int getDislikes() {
		return this.dislikes;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentID;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + dislikes;
		result = prime * result + likes;
		result = prime * result + post;
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
		if (dislikes != other.dislikes)
			return false;
		if (likes != other.likes)
			return false;
		if (post != other.post)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
