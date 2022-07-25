package com.unknown.supportapp.dto.ownedProduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.unknown.supportapp.dto.acccount.AccountDto;
import com.unknown.supportapp.dto.product.ProductDto;
import com.unknown.supportapp.dto.serializers.CustomAccountSerializer;
import com.unknown.supportapp.entities.Product;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnedProductDto {

    private Long id;

    @JsonSerialize(using = CustomAccountSerializer.class)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AccountDto owner;

    private String serialNumber;

    private ProductDto product;

}
