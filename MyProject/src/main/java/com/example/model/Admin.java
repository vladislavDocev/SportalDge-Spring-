package com.example.model;

import java.util.Collections;
import java.util.TreeSet;

public class Admin extends User{
	
	private TreeSet<Post> posts; //Post

	public void addPost(Post p){
		posts.add(p);
	}

	public TreeSet<Post> getPosts() {
		TreeSet<Post> temp = (TreeSet<Post>) Collections.unmodifiableSet(this.posts);
		return temp;
	}
	
}
