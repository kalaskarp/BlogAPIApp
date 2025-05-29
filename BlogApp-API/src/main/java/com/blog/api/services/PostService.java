package com.blog.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.api.entities.Post;
import com.blog.api.payloads.PostDTO;

@Service
public interface PostService {

	// create Post

	PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId);

	// Update Post

	Post updatePost(PostDTO postDTO, Integer postId);

	// Delete Post
	void deletePost(Integer postId);

	// get all posts
	List<PostDTO> getAllPosts();

	// get single post
	PostDTO getPostById(Integer postId);

	// get all posts by category
	List<PostDTO> getPostsbyCategory(Integer categoryId);

	// get all posts by user
	List<PostDTO> getPostsByUser(Integer userId);

	// search post

	List<PostDTO> searchPosts(String keyword);

}
