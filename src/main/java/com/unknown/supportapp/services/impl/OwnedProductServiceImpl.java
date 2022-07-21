package com.unknown.supportapp.services.impl;


import com.unknown.supportapp.dao.OwnedProductDao;
import com.unknown.supportapp.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.entities.OwnedProduct;
import com.unknown.supportapp.entities.converters.Converter;
import com.unknown.supportapp.entities.converters.OwnedProductConverter;
import com.unknown.supportapp.services.OwnedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OwnedProductServiceImpl implements OwnedProductService {

    private OwnedProductDao ownedProductDao;

    private Converter converter;

    public OwnedProductServiceImpl() {
    }

    @Autowired
    public OwnedProductServiceImpl(OwnedProductDao ownedProductDao, Converter converter) {
        this.ownedProductDao = ownedProductDao;
        this.converter = converter;
    }

    @Override
    public List<OwnedProductDto> loadUsersProducts(String email) {
        List<OwnedProduct> products = ownedProductDao.loadUsersProducts(email);
        List<OwnedProductDto> ownedProductDtos = converter.convertOwnedProductToDto(products);
        return ownedProductDtos;
    }
    @Override
    public void saveProduct(OwnedProductDto productDto) {
        OwnedProduct ownedProduct = converter.convertOwnedProductToEntity(productDto);
        ownedProductDao.saveProduct(ownedProduct);
    }
    @Override
    public boolean changeSerial(String oldValue, String newValue) {
        boolean result = ownedProductDao.changeSerial(oldValue, newValue);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        ownedProductDao.deleteById(id);
    }

    @Override
    public boolean checkSerial(String serialNumber) {
        if (serialNumber.trim().length() == 0){
            return false;
        }
        boolean result = ownedProductDao.checkSerial(serialNumber);
        return result;
    }

    @Override
    public OwnedProductDto loadById(Long id) {
        OwnedProduct entity = ownedProductDao.loadById(id);
        OwnedProductDto productDto = converter.convertOwnedProductToDto(entity);
        return productDto;
    }

    @Override
    public String loadModelById(Long id) {
        String model = ownedProductDao.loadModelById(id);
        return model;
    }
}
