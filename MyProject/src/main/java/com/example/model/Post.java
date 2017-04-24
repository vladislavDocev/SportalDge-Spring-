package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Post {

	private static int UNIQUE_ID = 1;
	private int postID;
	private String content;
	private String header;
	private String date;
	private String authorId;
	private String category;
	private ArrayList<Media> pictures;
	private ArrayList<Comment> comments;

	public Post(String content, String header, String category) {
		this.content = content;
		this.header = header;
		this.postID = UNIQUE_ID++;
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

	public String getAutor() {
		return this.authorId;
	}

	public String getCategory() {
		return this.category;
	}

	public List<Media> getPictures() {
		List<Media> temp = Collections.unmodifiableList(this.pictures);
		return temp;
	}

	public List<Comment> getComments() {
		List<Comment> temp = Collections.unmodifiableList(this.comments);
		return temp;
	}

	@Override
	public String toString() {
		return "Post [content=" + content + ", header=" + header + ", date=" + date + ", authorId=" + authorId
				+ ", pictures=" + pictures + ", comments=" + comments + "]";
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

	public void setAuthor(String u) {
		this.authorId = u;
	}

}
