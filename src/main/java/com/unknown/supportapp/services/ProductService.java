package com.unknown.supportapp.services;

import com.unknown.supportapp.dto.product.ProductDto;

import java.util.List;

public interface ProductService {

    List<String> loadAllProductTypes();

    List<ProductDto> loadAllProducts();

    List<ProductDto> loadProductsByType(String type);

    Long loadIdByModel(String model);
}
