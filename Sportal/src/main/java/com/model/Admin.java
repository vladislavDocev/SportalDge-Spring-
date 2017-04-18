package com.model;

import com.model.Post;
import com.model.Post.PostCategory;

public class Admin extends User{

	public Admin(String name, String userName, String password, String eMail) {
		super(name, userName, password, eMail);
		this.isAdmin = true;
	}
	
	public void doPost(String content, PostCategory category){
		Post post = new Post(content, this, category);
	}
	
}
