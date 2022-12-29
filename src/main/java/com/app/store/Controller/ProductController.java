package com.app.store.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.store.Dtos.ProductDto;
import com.app.store.Service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    
    @GetMapping()
    public List<ProductDto> getAll() {
        List<ProductDto> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/category/{category}")
    public List<ProductDto> getByCategory(@PathVariable String category) {
        List<ProductDto> products = productService.getProductsByCategory(category);
        return products;
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto product) {
        return productService.addProduct(product);
    }
}
