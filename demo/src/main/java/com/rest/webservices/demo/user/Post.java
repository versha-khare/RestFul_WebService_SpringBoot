package com.rest.webservices.demo.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	@Id
	@GeneratedValue
	private int postId;
	private String postMessage;
	
	
	
	
	

	protected Post() {
		super();
	}


	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postMessage=" + postMessage + "]";
	}


	public Post(int postId, String postMessage) {
		super();
		
		this.postId = postId;
		this.postMessage = postMessage;
	}

	

	


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostMessage() {
		return postMessage;
	}
	public void setPostMessage(String postMessage) {
		this.postMessage = postMessage;
	}
	
	

}
