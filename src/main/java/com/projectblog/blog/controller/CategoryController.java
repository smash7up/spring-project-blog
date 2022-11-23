package com.projectblog.blog.controller;

import com.projectblog.blog.entities.Category;
import com.projectblog.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping("/categories")
    @ResponseBody
    public List<Category> getCategory() {
        List<Category> categoryList = repository.findAll();
        return categoryList;
    }

    @GetMapping("/category")
    @ResponseBody
    public Category getCategoryById(@RequestParam(required = false) Long id) {
        Category category = new Category();
        if (id != null) {
            Optional<Category> optionalCategory = repository.findById(id);
            if (optionalCategory.isPresent()) {
                category = optionalCategory.get();
            }
        }
        return category;
    }

    @PostMapping("/category/list")
    public void postCategory(@RequestBody Category category) {
        Category newCategory = repository.save(category);
    }

    @DeleteMapping("/category/delete")
    public String deleteCategory(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/category";
    }
}
