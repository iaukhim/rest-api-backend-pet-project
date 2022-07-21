package com.unknown.supportapp.entities.converters;


import com.unknown.supportapp.dto.acccount.AccountDto;
import com.unknown.supportapp.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceUtil;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountConverter {

    @Autowired
    PersistenceUtil persistenceUtil;
    private TicketConverter ticketConverter = new TicketConverter();

    private OwnedProductConverter ownedProductConverter = new OwnedProductConverter(persistenceUtil, this);
    public AccountDto convertToDto(Account entity){
        AccountDto accountDto = new AccountDto();

        if(entity.getId() != null){
            accountDto.setId(entity.getId());
        }

        if(entity.getEmail() != null){
            accountDto.setEmail(entity.getEmail());
        }
        if(entity.getPassword() != null){
            accountDto.setPassword(entity.getPassword());
        }
        if(entity.getName() != null){
            accountDto.setName(entity.getName());
        }
        if(entity.getSurname() != null){
            accountDto.setSurname(entity.getSurname());
        }
        if(entity.getPhoneNumber() != null){
            accountDto.setPhoneNumber(entity.getPhoneNumber());
        }
        if(entity.getDateOfBirth() != null){
            accountDto.setDateOfBirth(entity.getDateOfBirth());
        }


        if (persistenceUtil.isLoaded(entity, "ownedProducts1")) {
            accountDto.setOwnedProducts(ownedProductConverter.convertToDtoList(entity.getOwnedProducts()));
        }

        if (persistenceUtil.isLoaded(entity, "tickets")) {
            accountDto.setTickets(ticketConverter.convertToDtoList(entity.getTickets()));
        }

        return accountDto;
    }

    public List<AccountDto> convertToDto(List<Account> entityList){
        List <AccountDto> dtos = new ArrayList<>();
        for (Account entity: entityList) {
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    public Account convertToEntity(AccountDto accountDto){
        Account accountEntity = new Account();

        if(accountDto.getId() != null){
            accountEntity.setId(accountDto.getId());
        }

        if(accountDto.getEmail() != null){
            accountEntity.setEmail(accountDto.getEmail());
        }
        if(accountDto.getPassword() != null){
            accountEntity.setPassword(accountDto.getPassword());
        }
        if(accountDto.getName() != null){
            accountEntity.setName(accountDto.getName());
        }
        if(accountDto.getSurname() != null){
            accountEntity.setSurname(accountDto.getSurname());
        }
        if(accountDto.getPhoneNumber() != null){
            accountEntity.setPhoneNumber(accountDto.getPhoneNumber());
        }
        if(accountDto.getDateOfBirth() != null){
            accountEntity.setDateOfBirth(accountDto.getDateOfBirth());
        }

        if(accountDto.getOwnedProducts() != null){
            accountEntity.setOwnedProducts(ownedProductConverter.convertToEntityList(accountDto.getOwnedProducts()));
        }


        if(accountDto.getTickets() != null){
            accountEntity.setTickets(ticketConverter.convertToEntityList(accountDto.getTickets()));
        }
        return accountEntity;
    }
}
