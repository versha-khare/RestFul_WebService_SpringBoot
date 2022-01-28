package com.rest.webservices.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Size(min =2)
	private String name;
	
	@Past
	private Date date;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	/*
	 * private Map<Integer,List<Post>> map=new HashMap<Integer,List<Post>>();
	 * 
	 * 
	 * public Map<Integer, List<Post>> getMap() { return map; }
	 * 
	 * public void setMap(Map<Integer, List<Post>> map) { this.map = map; }
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
	

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", date=" + date + "]";
	}

	public User(int id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}

	protected User() {
		super();
	}

	
	


	
	
	
	

}
