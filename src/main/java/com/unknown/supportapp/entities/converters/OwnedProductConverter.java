package com.unknown.supportapp.entities.converters;

import com.unknown.supportapp.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.entities.OwnedProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceUtil;
import java.util.ArrayList;
import java.util.List;

@Component
public class OwnedProductConverter {

    private PersistenceUtil persistenceUtil;
    private AccountConverter accountConverter;
    @Autowired
    public OwnedProductConverter(PersistenceUtil persistenceUtil, AccountConverter accountConverter) {
        this.persistenceUtil = persistenceUtil;
        this.accountConverter = accountConverter;
    }

    public OwnedProduct convertToEntity(OwnedProductDto dto){
        OwnedProduct ownedProduct = new OwnedProduct();

        if(dto.getId()!= null){
            ownedProduct.setId(dto.getId());
        }

       /* if(dto.getOwnerId()!= null){
            ownedProduct.setOwnerId(dto.getOwnerId());
        }

        if(dto.getType()!= null){
            ownedProduct.setType(dto.getType());
        }
        if(dto.getModel()!= null){
            ownedProduct.setModel(dto.getModel());
        }
        if(dto.getSerialNumber()!= null){
            ownedProduct.setSerialNumber(dto.getSerialNumber());
        }*/


        if(dto.getOwner()!= null){
            ownedProduct.setOwner(accountConverter.convertToEntity(dto.getOwner()));
        }


        return ownedProduct;
    }

    public OwnedProductDto convertToDto(OwnedProduct entity){
        OwnedProductDto dto = new OwnedProductDto();

        if(entity.getId()!= null){
            dto.setId(entity.getId());
        }
/*
        if(entity.getOwnerId()!= null){
            dto.setOwnerId(entity.getOwnerId());
        }

        if(entity.getType()!= null){
            dto.setType(entity.getType());
        }
        if(entity.getModel()!= null){
            dto.setModel(entity.getModel());
        }
        if(entity.getSerialNumber()!= null){
            dto.setSerialNumber(entity.getSerialNumber());
        }*/

        if(persistenceUtil.isLoaded(entity, "owner")){
            dto.setOwner(accountConverter.convertToDto(entity.getOwner()));
        }

        return dto;
    }

    public List<OwnedProductDto> convertToDtoList(List<OwnedProduct> entityList){
        List<OwnedProductDto> dtoList = new ArrayList<>();

        for (OwnedProduct entity: entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    public List<OwnedProduct> convertToEntityList(List<OwnedProductDto> dtoList){
        List<OwnedProduct> entityList = new ArrayList<>();

        for (OwnedProductDto dto: dtoList) {
            entityList.add(convertToEntity(dto));
        }
        return entityList;
    }
}
