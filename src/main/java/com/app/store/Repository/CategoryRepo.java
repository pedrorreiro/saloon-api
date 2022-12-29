package com.app.store.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.store.Model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
    
    List<Category> findAllByActiveTrue();

    List<Category> findByNameAndActiveTrue(String name);

    List<Category> findByName_InAndActiveTrue(List<String> categories);
}
