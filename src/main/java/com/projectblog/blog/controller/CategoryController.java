package com.projectblog.blog.controller;

import com.projectblog.blog.entities.CategoryEntity;
import com.projectblog.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping("/categories")
    @ResponseBody
    public List<CategoryEntity> getCategory() {
        List<CategoryEntity> categoryList = repository.findAll();
        return categoryList;
    }

    @GetMapping("/category")
    @ResponseBody
    public CategoryEntity getCategoryById(@RequestParam(required = false) Long id) {
        CategoryEntity category = new CategoryEntity();
        if (id != null) {
            Optional<CategoryEntity> optionalCategory = repository.findById(id);
            if (optionalCategory.isPresent()) {
                category = optionalCategory.get();
            }
        }
        return category;
    }

    @PostMapping("/category/list")
    public void postCategory(@RequestBody CategoryEntity category) {
        CategoryEntity newCategory = repository.save(category);
    }

    @DeleteMapping("/category/delete")
    public String deleteCategory(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/category";
    }
}
