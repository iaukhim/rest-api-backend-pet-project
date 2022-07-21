package com.unknown.supportapp.dao;


import com.unknown.supportapp.entities.OwnedProduct;

import java.util.List;

public interface OwnedProductDao {

    OwnedProduct saveProduct(OwnedProduct product);

    List<OwnedProduct> loadUsersProducts (String email);

    void deleteById(Long id);

    boolean changeSerial (String oldValue, String newValue);

    boolean checkSerial (String serialNumber);

    OwnedProduct loadById(Long id);

    String loadModelById(Long id);
}
