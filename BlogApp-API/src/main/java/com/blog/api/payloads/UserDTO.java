package com.blog.api.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

	
	private int id;
	
	@NotEmpty
	private String name;
	
	@Email(message="Enter valid Email Id")
	private String email;
	
	@NotEmpty(message = "Password should be not empty")
	private String password;
	
	@NotEmpty
	private String about;
}
