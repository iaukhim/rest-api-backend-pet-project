package com.unknown.supportapp.dao;


import com.unknown.supportapp.entities.OwnedProduct;

import java.util.List;

public interface OwnedProductDao extends AbstractDao<OwnedProduct>{

    List<OwnedProduct> loadUsersProducts (String email);

    boolean changeSerial (String oldValue, String newValue);

    boolean checkSerial (String serialNumber);

    String loadModelById(Long id);
}
