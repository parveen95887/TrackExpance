package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Category;
import com.example.demo.repository.CatagoryRepository;

@Service
public class CatagoryService {

	    @Autowired
	    private CatagoryRepository categoryRepository;

	    public Category saveCategory(Category category) {
	        return categoryRepository.save(category);
	    }

	    public List<Category> getAllCategories() {
	        return categoryRepository.findAll();
	    }

	    public Optional<Category> getCategoryById(Long id) {
	        return categoryRepository.findById(id);
	    }

	    public void deleteCategory(Long id) {
	        categoryRepository.deleteById(id);
	    }
	    
	    // Update existing category
	    public Category updateCategory(Long id, Category updatedCategory) {
	        return categoryRepository.findById(id)
	                .map(category -> {
	                    category.setCategoryName(updatedCategory.getCategoryName());
	                    category.setDescription(updatedCategory.getDescription());
	                    return categoryRepository.save(category);
	                })
	                .orElseThrow(() -> new RuntimeException("Category not found"));
	    }
	
	
	
}
