package com.blog.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.entities.User;
import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.UserDTO;
import com.blog.api.services.UserService;
import com.blog.api.services.impl.userServiceImpl;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private userServiceImpl serviceImpl;

	// Healthcheck
	@GetMapping("/demo")
	public ResponseEntity<String> healthCheck(HttpServletRequest httpServlet) {
		String string = httpServlet.getSession().getId();

		return ResponseEntity.ok("Health Check is Fine, Session Id: " + string);
	}

	// Create User API
	@PostMapping("/register")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		UserDTO user = this.userService.createUser(userDTO);

		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	// Login API
	@PostMapping("/login")
	public String login(@RequestBody UserDTO user) {

		String verified = serviceImpl.verify(user);

		return verified;
	}

	// Update User API
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,
			@PathVariable("userId") Integer userId) {

		UserDTO updateUser = userService.updateUser(userDTO, userId);

		return ResponseEntity.ok(updateUser);

	}

	// Delete User API
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@Valid @PathVariable("userId") Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("User Deleted...!", true), HttpStatus.OK);
	}

	// get All users API
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	// Get User by ID
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getSingleUser(@Valid @PathVariable Integer userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}

}
