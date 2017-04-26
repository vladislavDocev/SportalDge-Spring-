package com.example.model;

import java.util.Collections;
import java.util.TreeSet;

public class Admin extends User{
	
	private TreeSet<Post> posts; //Post

	public Admin(String name, String userName, String password, String eMail) {
		super(name, userName, password, eMail);
		this.isAdmin = 1;
		this.posts = new TreeSet<>();
	}
	
	public void addPost(Post p){
		posts.add(p);
	}

	public TreeSet<Post> getPosts() {
		TreeSet<Post> temp = (TreeSet<Post>) Collections.unmodifiableSet(this.posts);
		return temp;
	}
	
}
