package com.example.model;

public class Like {

	private int id;
	private User user;
	private Comment comment;
	
	public Like(int id, User user, Comment comment) {
		super();
		this.id = id;
		this.user = user;
		this.comment = comment;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getUser() {
		return this.user.getId();
	}
	
	public int getComment() {
		return this.comment.getCommentID();
	}
}
