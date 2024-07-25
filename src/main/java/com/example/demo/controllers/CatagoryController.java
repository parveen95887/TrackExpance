package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.Category;
import com.example.demo.Services.CatagoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/catagories")
public class CatagoryController {

	@Autowired
    private  CatagoryService categoryService;

	
    // Show create category form
    @GetMapping("/showform")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("categories", new Category());
        return "catagoryAdd";
    }

    @GetMapping("/getcatagorylist")
    public String showCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "catagoryList";
    }
    
    @PostMapping("/create")
    public String createCategory(@Valid @ModelAttribute Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "categoryAdd";
        }
        categoryService.saveCategory(category);
        return "redirect:/catagories/getcatagorylist";
    }
    
    
      // List all categories
    @GetMapping("/getallcat")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }
/*
    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        List<Category> categories = categoryService.getAllCategories(); // Fetch all categories
        model.addAttribute("category", category);
        model.addAttribute("categories", categories); // Add categories list to model
        return "editCategory";
    }
    */
    // Show edit category form
    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("category", category);
        
        return "editCatagory";
    }

    // Update category
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @Valid @ModelAttribute Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "editCatagory";
        }
        category.setCategoryId(id);
        categoryService.saveCategory(category);
        return "redirect:/catagories/getcatagorylist";
       
       
    }

    // Delete category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/catagories/getcatagorylist";
    }

}
