package com.unknown.supportapp.services;


import com.unknown.supportapp.dto.ownedProduct.OwnedProductDto;

import java.util.List;

public interface OwnedProductService {

    List<OwnedProductDto> loadUsersProducts(String email);

    void deleteById(Long id);

    boolean changeSerial(String oldValue, String newValue);

    void saveProduct(OwnedProductDto productDto);

    boolean checkSerial(String serialNumber);

    OwnedProductDto loadById(Long id);

    String loadModelById(Long id);
}
