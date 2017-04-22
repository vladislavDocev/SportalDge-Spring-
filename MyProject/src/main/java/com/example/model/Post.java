package com.example.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.TreeSet;

public class Post {
	
	public enum PostCategory{}
	
	private static int UNIQUE_ID = 1;
	private int postID;
	private String content;
	private String header;
	private LocalDateTime date;
	private int authorId;
	private PostCategory category;
	private TreeSet<Media> pictures;
	private TreeSet<Comment> comments;
	
	public Post(String content,String header, int authorId) {
		this.content = content;
		this.header = header;
		this.authorId = authorId;
		this.postID = UNIQUE_ID++;
		this.date = LocalDateTime.now();
		this.pictures = new TreeSet<>();
		this.comments = new TreeSet<>();
	}

	public Post() {}

	public int getPostID(){
		return this.postID;
	}

	public String getContent(){
		return this.content;
	}
	
	public String getHeader(){
		return this.header;
	}

	public String getDate() {
		return this.date.toString();
	}

	public int getAutor() {
		return this.authorId;
	}

	public String getCategory() {
		return this.category.name();
	}

	public TreeSet<Media> getPictures() {
		TreeSet<Media> temp = (TreeSet<Media>) Collections.unmodifiableSet(this.pictures);
		return temp;
	}

	public TreeSet<Comment> getComments() {
		TreeSet<Comment> temp = (TreeSet<Comment>) Collections.unmodifiableSet(this.comments);
		return temp;
	}

	@Override
	public String toString() {
		return "Post [content=" + content + ", header=" + header + ", date=" + date + ", authorId=" + authorId
				+ ", pictures=" + pictures + ", comments=" + comments + "]";
	}
	
}
