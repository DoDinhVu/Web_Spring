package edu.poly.web_spring.service;

import edu.poly.web_spring.dto.CategoryDto;
import edu.poly.web_spring.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
	Category save(Category category);
	
	Category update(Category category);
	
	List<Category> findAllByActivatedTrue();
	
	List<Category> findALl();
	
	Optional<Category> findById(Long id);
	
	void deleteById(Long id);
	
	void enableById(Long id);
	
	List<CategoryDto> getCategoriesAndSize();
}
