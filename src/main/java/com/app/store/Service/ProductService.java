package com.app.store.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.store.Dtos.ProductDto;
import com.app.store.Model.Category;
import com.app.store.Model.Product;
import com.app.store.Repository.CategoryRepo;
import com.app.store.Repository.ProductRepo;

@Service
public class ProductService {
    
    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    // @Autowired
    // ProductCategoryRepo productCategoryRepo;

    public List<ProductDto> getAllProducts() {
        System.out.println("Getting all products");

        List<Product> products = productRepo.findAll();
        
        List<ProductDto> productsDto = products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            productDto.setCategories(product.getCategories().stream().map(category -> category.getName()).collect(Collectors.toList()));
            return productDto;
        }).collect(Collectors.toList());

       
        
        return productsDto;
    }

    public List<ProductDto> getProductsByName(String name) {

        List<Product> products = productRepo.findByNameAndActiveTrue(name);

        List<ProductDto> productsDto = products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            productDto.setCategories(product.getCategories().stream().map(category -> category.getName()).collect(Collectors.toList()));
            return productDto;
        }).collect(Collectors.toList());


        return productsDto;
    }

    public List<ProductDto> getProductsByCategory(String category) {

        List<Product> products = productRepo.findByCategories_NameAndActiveTrue(category);

        List<ProductDto> productsDto = products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            productDto.setCategories(product.getCategories().stream().map(c -> c.getName()).collect(Collectors.toList()));
            return productDto;
        }).collect(Collectors.toList());

        return productsDto;
    }

    public ProductDto addProduct(ProductDto product) {

        // converte dto em model

        Product newProduct = new Product();

        BeanUtils.copyProperties(product, newProduct);

        List<Category> categories = categoryRepo.findByName_InAndActiveTrue(product.getCategories());

        newProduct.setCategories(categories);

        Product productSaved = productRepo.save(newProduct);

        ProductDto productToReturn = new ProductDto();

        BeanUtils.copyProperties(productSaved, productToReturn);
        
        return productToReturn;
    }
}
