package com.unknown.supportapp.controllers.rest;


import com.unknown.supportapp.dto.product.ProductDto;
import com.unknown.supportapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductDto> loadAllProducts(){
        return productService.loadAllProducts();
    }

    @GetMapping("/types")
    public List<String> loadAllProductTypes(){
        return productService.loadAllProductTypes();
    }

    @GetMapping("/{type}")
    public List<ProductDto> loadProductsByType(@PathVariable String type){
        return productService.loadProductsByType(type);
    }
}
