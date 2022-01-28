package com.rest.webservices.demo.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJpaResource {
	@Autowired
	private UserDaoService service;

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private PostRepo postRepo;

	@GetMapping("/jpa/users")
	public List<User> retrieveUsers() {
		return repo.findAll();
		// return users;
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<Optional<User>> retrieveUser(@PathVariable int id) {
		Optional<User> u = repo.findById(id);
		if (!u.isPresent()) {
			throw new UserNotFoundException("id- " + id);
		}

		EntityModel<Optional<User>> model = EntityModel.of(u);
		WebMvcLinkBuilder linkUsers = linkTo(methodOn(this.getClass()).retrieveUsers());
		model.add(linkUsers.withRel("all-users"));

		return model;
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repo.deleteById(id);

		// return u;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User Saved = repo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Saved.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveALLPost(@PathVariable int id) {
		Optional<User> u = repo.findById(id);

		if (!u.isPresent()) {
			throw new UserNotFoundException("id- " + id);
		}
		return u.get().getPosts();
	}

	@GetMapping("/jpa/users/{id}/posts/{post_id}")
	public Post retriveOnePost(@PathVariable int id, @PathVariable int post_id) {
		Optional<User> u = repo.findById(id);
		Post k = null;
		if (u.isPresent()) {
			List<Post> p = u.get().getPosts();
			for (Post i : p) {
				if (i.getPostId() == post_id) {
					k = i;
				}
			}
		}

		return k;
	}

	
	  @PostMapping("/jpa/users/{id}/posts") 
	  public ResponseEntity<Object> createPost(@RequestBody Post post , @PathVariable int id)
	  { 
		  Optional<User> u = repo.findById(id);
			Post k = null ;
			if (u.isPresent()) {
				post.setUser(u.get());
				k = postRepo.save(post);
			}
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(k.getPostId())
					.toUri();
			return ResponseEntity.created(location).build();
		  
	  }
	  
	 

}
