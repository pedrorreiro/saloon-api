package com.app.store.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.store.Dtos.CategoryDto;
import com.app.store.Model.Category;
import com.app.store.Service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    
    @GetMapping
    public List<CategoryDto> getAll() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return categories;
    }
    
    @PostMapping
    public Category addCategory(@RequestBody CategoryDto category) {
        return categoryService.addCategory(category);
    }
}
