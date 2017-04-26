package com.example.model;

public class Media {

	private int mediaID;
	private String mediaLink;
	private Post post;
	
	public Media(int mediaID,String mediaLink, Post post) {
		this.mediaLink = mediaLink;
		this.post = post;
		this.mediaID = mediaID;
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mediaID;
		result = prime * result + ((mediaLink == null) ? 0 : mediaLink.hashCode());
		result = prime * result + ((post == null) ? 0 : post.hashCode());
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
		Media other = (Media) obj;
		if (mediaID != other.mediaID)
			return false;
		if (mediaLink == null) {
			if (other.mediaLink != null)
				return false;
		} else if (!mediaLink.equals(other.mediaLink))
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		return true;
	}

	public void setId(int id) {
		this.mediaID = id;
	}
}
