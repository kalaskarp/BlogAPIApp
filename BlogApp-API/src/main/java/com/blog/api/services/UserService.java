package com.blog.api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.blog.api.payloads.UserDTO;

@Service
public interface UserService {

	UserDTO createUser(UserDTO user);
	
	UserDTO updateUser(UserDTO user,Integer userId);
	
	UserDTO getUserById(Integer userId);
	
	List<UserDTO> getAllUsers();
	
	void deleteUser(Integer userId);
	
	String verify(UserDTO userDto);
	
}
