package com.example.model;

public class Media {

	private int mediaID;
	private String mediaLink;
	private int post;
	
	public Media(int mediaID,String mediaLink, int post) {
		this.mediaLink = mediaLink;
		this.post = post;
		this.mediaID = mediaID;
	}
	
	public String getMediaLink() {
		return mediaLink;
	}
	
	public int getPost() {
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
		result = prime * result + post;
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
		if (post != other.post)
			return false;
		return true;
	}
}
