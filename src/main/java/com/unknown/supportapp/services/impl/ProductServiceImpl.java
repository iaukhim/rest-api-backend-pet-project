package com.unknown.supportapp.services.impl;



import com.unknown.supportapp.dao.ProductDao;
import com.unknown.supportapp.dto.product.ProductDto;
import com.unknown.supportapp.entities.Product;
import com.unknown.supportapp.entities.converters.ProductConverter;
import com.unknown.supportapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    private ProductConverter productConverter;

    public ProductServiceImpl() {
    }

    @Autowired
    public ProductServiceImpl(ProductDao productDao, ProductConverter productConverter) {
        this.productDao = productDao;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductDto> loadProductsByType(String type) {
        List<Product> products = productDao.loadProductsByType(type);
        return productConverter.convertToDtoList(products);
    }

    @Override
    public List<ProductDto> loadAllProducts() {
        List<Product> products = productDao.loadAllProducts();
        return productConverter.convertToDtoList(products);
    }

    @Override
    public List<String> loadAllProductTypes() {
        return productDao.loadAllProductTypes();
    }

    @Override
    public Long loadIdByModel(String model) {
        Long id = productDao.loadIdByModel(model);
        return id;
    }
}
