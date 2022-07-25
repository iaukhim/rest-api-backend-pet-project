package com.unknown.supportapp.dao;


import com.unknown.supportapp.entities.Product;

import java.util.List;

public interface ProductDao extends AbstractDao<Product> {

    List<String> loadAllProductTypes();

    List<Product> loadProductsByType(String type);

    Long loadIdByModel(String model);


}
