package com.unknown.supportapp.dto.ownedProduct;

import com.unknown.supportapp.dto.acccount.AccountDto;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnedProductDto {

    private Long id;

    private Long ownerId;

    private String type;

    private String model;

    private String serialNumber;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AccountDto owner;

    public OwnedProductDto(Long id, Long ownerId, String type, String model, String serialNumber) {
        this.id = id;
        this.ownerId = ownerId;
        this.type = type;
        this.model = model;
        this.serialNumber = serialNumber;
    }
}
