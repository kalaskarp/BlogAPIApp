package com.blog.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entities.Category;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.CategoryDTO;
import com.blog.api.repositories.CategoryRepo;
import com.blog.api.services.CategoryService;

@Service
public class catageryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDTO createCatagery(CategoryDTO categoryDTO) {
		Category cat = mapper.map(categoryDTO, Category.class);
		Category addedCat = categoryRepo.save(cat);

		return mapper.map(addedCat, CategoryDTO.class);
	}

	// Update Category
	@Override
	public CategoryDTO updateCatagery(CategoryDTO categoryDTO, Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

		cat.setCatrgoryTitle(categoryDTO.getCatrgoryTitle());
		cat.setCategoryDescription(categoryDTO.getCategoryDescription());

		Category updateCategory = categoryRepo.save(cat);

		return mapper.map(updateCategory, CategoryDTO.class);
	}

	// Delete Category
	@Override
	public void deleteCatagery(Integer categoryId) {

		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		categoryRepo.delete(cat);
	}

	// Get Category By ID
	@Override
	public CategoryDTO getCatagery(Integer categoryId) {

		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

		return mapper.map(cat, CategoryDTO.class);
	}

	// Get all Category
	@Override
	public List<CategoryDTO> getCategories() {

		List<CategoryDTO> catDtos = categoryRepo.findAll().stream().map((cat) -> mapper.map(cat, CategoryDTO.class))
				.collect(Collectors.toList());

		return catDtos;
	}

}
