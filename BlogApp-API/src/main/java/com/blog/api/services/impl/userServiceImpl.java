package com.blog.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.api.exceptions.*;
import com.blog.api.entities.User;
import com.blog.api.payloads.UserDTO;
import com.blog.api.repositories.UserRepo;
import com.blog.api.services.UserService;

@Service
public class userServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTServiceImpl jwtServiceImpl;

	private User user;

	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user = this.dtoToUser(userDto);

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		User savedUser = this.userRepo.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());

		User UpdateUser = this.userRepo.save(user);

		return this.userToDTO(UpdateUser);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {

		List<User> users = userRepo.findAll();

		List<UserDTO> userDtos = users.stream().map(user -> userToDTO(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User User = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		userRepo.delete(User);

	}

	private User dtoToUser(UserDTO userDto) {
		User user = modelMapper.map(userDto, User.class);
		return user;
	}

	private UserDTO userToDTO(User user) {

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;

	}

	public String verify(UserDTO userDto) {

		user = dtoToUser(userDto);

		org.springframework.security.core.Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
		
		if (authentication.isAuthenticated()) {

			return jwtServiceImpl.generateToken(user.getName());
		}
		return "Username and Password Not Found";
	}

}
