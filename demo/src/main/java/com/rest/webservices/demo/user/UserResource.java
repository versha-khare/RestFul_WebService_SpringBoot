package com.rest.webservices.demo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveUsers()
	{
		List<User> users = service.findALLUser();
		return users;
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser( @PathVariable int id)
	{
		User u = service.findOneUser(id);
		if(u == null)
		{
			throw new UserNotFoundException("id- "+id);
		}
		EntityModel<User> model = EntityModel.of(u);
		WebMvcLinkBuilder linkUsers= linkTo(methodOn(this.getClass()).retrieveUsers());
		model.add(linkUsers.withRel("all-users"));
		return model;
	}
	
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User u = service.deleteUser(id);
		if (u == null) {
			throw new UserNotFoundException("id- " + id);
		}
		return u;
	}
	 
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user)
	{
		User Saved = service.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Saved.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	/*
	 * @GetMapping("/users/{id}/posts") public List<Post>
	 * retrieveALLPost(@PathVariable int id) { List<Post> posts =
	 * service.FindALLPost(id); return posts; }
	 */
	
	/*
	 * @GetMapping("/users/{id}/posts/{post_id}") public Post
	 * retriveOnePost(@PathVariable int id , @PathVariable int post_id) { Post p =
	 * service.FindOnePost(id, post_id); return p; }
	 * 
	 * @PostMapping("/users/{id}/posts") public void createPost(@RequestBody Post
	 * post , @PathVariable int id) { Post p = service.SavePost(post, id); }
	 */
	
	

}
