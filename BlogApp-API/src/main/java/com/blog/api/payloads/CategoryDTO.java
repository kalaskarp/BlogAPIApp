package com.blog.api.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private int categoryId;

	@NotBlank(message = "Please Add Category Title")
	private String catrgoryTitle;

	@NotBlank(message = "Please Add Description")
	private String categoryDescription;
}
