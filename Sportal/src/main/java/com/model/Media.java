package com.model;

public class Media {
	
	private static int UNIQUE_ID = 1;
	private int mediaID;
	private String mediaLink;
	private Post post;
	
	public Media(String mediaLink, Post post) {
		this.mediaLink = mediaLink;
		this.post = post;
		this.mediaID = UNIQUE_ID++;
	}
	
	public String getMediaLink() {
		return mediaLink;
	}
	
	public Post getPost() {
		return post;
	}

	public int getMediaID() {
		return mediaID;
	}
	
}
