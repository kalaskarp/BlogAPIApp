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

import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.CategoryDTO;
import com.blog.api.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		
		CategoryDTO createCategory = categoryService.createCatagery(categoryDTO);
		
		return new ResponseEntity<CategoryDTO>(createCategory,HttpStatus.CREATED);
	}
	
	
	// Update by Id
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable("catId") int catId){
		
		CategoryDTO updatedCategory = categoryService.updateCatagery(categoryDTO,catId);
		
		return new ResponseEntity<CategoryDTO>(updatedCategory,HttpStatus.CREATED);
	}
	
	// Delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId){
		
		categoryService.deleteCatagery(catId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is Deleted...!", true),HttpStatus.CREATED);
	}
	
	// Get by Id
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("catId") Integer catId){
		
		CategoryDTO getCategoryById = categoryService.getCatagery(catId);
		
		return new ResponseEntity<CategoryDTO>(getCategoryById,HttpStatus.CREATED);
	}
	
	
	// Get All
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getCategoryAll(){
		
		List<CategoryDTO> getAllCategories = categoryService.getCategories();
		
		return ResponseEntity.ok(getAllCategories);
	}
	
}
