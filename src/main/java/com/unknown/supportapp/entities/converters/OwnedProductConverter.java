package com.unknown.supportapp.entities.converters;

import com.unknown.supportapp.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.entities.OwnedProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OwnedProductConverter {

    public OwnedProductConverter() {
    }

    public OwnedProduct convertToEntity(OwnedProductDto dto){
        OwnedProduct ownedProduct = new OwnedProduct();

        if(dto.getId()!= null){
            ownedProduct.setId(dto.getId());
        }

        if(dto.getOwnerId()!= null){
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
        }
        return ownedProduct;
    }

    public OwnedProductDto convertToDto(OwnedProduct entity){
        OwnedProductDto dto = new OwnedProductDto();

        if(entity.getId()!= null){
            dto.setId(entity.getId());
        }

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
}
