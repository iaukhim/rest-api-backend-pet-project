package com.unknown.supportapp.entities.converters;



import com.unknown.supportapp.dto.product.ProductDto;
import com.unknown.supportapp.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {
    public ProductConverter() {
    }

    public ProductDto convertToDto(Product entity){
        ProductDto productDto = new ProductDto();

        if(entity.getId() != null){
            productDto.setId(entity.getId());
        }

        if(entity.getType() != null){
            productDto.setType(entity.getType());
        }
        if(entity.getModel() != null){
            productDto.setModel(entity.getModel());
        }
        return productDto;
    }

    public List<ProductDto> convertToDtoList(List<Product> entityList){
        List<ProductDto> dtoList = new ArrayList<>();

        for (Product entity: entityList) {
            ProductDto dto = convertToDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
