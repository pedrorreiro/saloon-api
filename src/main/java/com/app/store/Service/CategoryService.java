package com.app.store.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.store.Dtos.CategoryDto;
import com.app.store.Model.Category;
import com.app.store.Repository.CategoryRepo;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepo categoryRepo;

    public List<CategoryDto> getAllCategories() {
        List<Category> c = categoryRepo.findAllByActiveTrue();

        List<CategoryDto> categories = c.stream().map(category -> {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(category, categoryDto);
            return categoryDto;
        }).collect(Collectors.toList());

        return categories;
    }

    public List<CategoryDto> getCategoriesByName(String name) {

        List<Category> c = categoryRepo.findByNameAndActiveTrue(name);

        List<CategoryDto> categories = c.stream().map(category -> {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(category, categoryDto);
            return categoryDto;
        }).collect(Collectors.toList());

        return categories;
    }

    public Category addCategory(CategoryDto category) {

        // converte dto em model

        Category newCategory = new Category();

        BeanUtils.copyProperties(category, newCategory);

        return categoryRepo.save(newCategory);
    }
}
