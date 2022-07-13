package com.unknown.supportapp.dao;


import com.unknown.supportapp.entities.Product;

import java.util.List;

public interface ProductDao {

    List<String> loadAllProductTypes();

    List<Product> loadAllProducts();

    List<Product> loadProductsByType(String type);

    Long loadIdByModel(String model);

}
