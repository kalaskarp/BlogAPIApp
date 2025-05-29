package com.blog.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payloads.PostDTO;
import com.blog.api.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	private PostDTO createPost;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId) {

		createPost = postService.createPost(postDTO, userId, categoryId);

		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}

	// Get By User by Id
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostUser(@PathVariable Integer userId) {

		List<PostDTO> posts = postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}

	// Get By category by Id
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostbycategory(@PathVariable Integer categoryId) {

		List<PostDTO> posts = postService.getPostsbyCategory(categoryId);

		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}

	// get all Posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getAllPosts() {
		List<PostDTO> allPosts = postService.getAllPosts();

		return new ResponseEntity<List<PostDTO>>(allPosts, HttpStatus.OK);
	}

	// get all post by Id
	@GetMapping("/posts/{postid}")
	public ResponseEntity<PostDTO> getPostsById(@PathVariable Integer postid) {
		PostDTO postById = postService.getPostById(postid);

		return new ResponseEntity<PostDTO>(postById, HttpStatus.OK);
	}

}
