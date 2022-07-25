package com.unknown.supportapp.dto.ticket;

import com.unknown.supportapp.dto.acccount.AccountDto;
import com.unknown.supportapp.dto.manager.ManagerDto;
import com.unknown.supportapp.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.entities.Account;
import com.unknown.supportapp.entities.Manager;
import com.unknown.supportapp.entities.OwnedProduct;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {

    private Long id;

    private OwnedProductDto ownedProduct;

    private String theme;

    private String text;

    private boolean status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AccountDto starter;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ManagerDto manager;
}
