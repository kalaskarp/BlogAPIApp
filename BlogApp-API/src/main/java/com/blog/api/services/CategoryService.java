package com.blog.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.api.payloads.CategoryDTO;

@Service
public interface CategoryService {

	// create
	CategoryDTO createCatagery(CategoryDTO categoryDTO);

	// update
	CategoryDTO updateCatagery(CategoryDTO categoryDTO, Integer categoryId);

	// delete
	public void deleteCatagery(Integer categoryId);

	// Get
	CategoryDTO getCatagery(Integer categoryId);

	// Getall
	List<CategoryDTO> getCategories();

}
