package com.app.store.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.store.Model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
    
    List<Product> findAllByActiveTrue();

    List<Product> findByNameAndActiveTrue(String name);

    List<Product> findByCategories_NameAndActiveTrue(String category);
}
