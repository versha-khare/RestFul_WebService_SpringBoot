package com.rest.webservices.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> us = new ArrayList<User>();
	private static List<Post> p = new ArrayList<Post>();
	//private static List<Post> p1 = new ArrayList<Post>();
	//private static Map<Integer , List<Post>> map = new HashMap<Integer , List<Post>>();
	private int count=3;
	private int countPost = 13;
	static {
		/*
		 * p.add(new Post(1, 11 , "Picture1")); p.add(new Post(1 ,12 , "Picture2"));
		 * p.add(new Post(2, 21 , "Scene1")); p.add(new Post(2, 22 , "Scene2"));
		 */
	us.add(new User(1, "Adam" , new Date()));
	us.add(new User(2, "John" , new Date()));
	/*
	 * for(User u : us) for(Post ps:p) { if(u.getId() == ps.getUserId()) {
	 * map.put(u.getId(), p); } u.setMap(map);
	 */
	
	
	}
	
	
	
	public List<User> findALLUser()
	{
		return us;
	}
	
	public User findOneUser(int id)
	{
		for(User user : us)
		{
			if(user.getId() == id)
			{
				return user;
			}
		}
		return null;
	}
	
	public User saveUser(User user)
	{
		user.setId(count++);
		us.add(user);
		
		return user;
	}
	
	/*
	 * public List<Post> FindALLPost(int userid) { List<Post> posts = new
	 * ArrayList<Post>(); for(Post ps : p) { if(ps.getUserId() == userid) {
	 * posts.add(ps); } } return posts; }
	 * 
	 * public Post FindOnePost(int userid , int postid) { for(Post ps:p)
	 * {if(ps.getUserId() == userid && ps.getPostId() == postid ) {
	 * 
	 * return ps;
	 * 
	 * } } return null; }
	 * 
	 * public Post SavePost(Post post , int id) { post.setUserId(id);
	 * post.setPostId(countPost++); p.add(post); return post; }
	 */
	 
	 public User deleteUser(int id)
	 {
		 for(User u :us)
		 {
			 if(u.getId() == id)
			 {
				 us.remove(u);
				 return u;
			 }
		 }
		 return null;
	 }
}

