package com.blog.api.payloads;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

	private int postId;
	
	private String Title;

	private String content;

	private String imageName = "default.png";

	private Date addDate;

	private CategoryDTO category;

	private UserDTO user;

}
