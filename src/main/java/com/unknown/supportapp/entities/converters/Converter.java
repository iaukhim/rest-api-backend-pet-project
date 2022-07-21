package com.unknown.supportapp.entities.converters;

import com.unknown.supportapp.dto.acccount.AccountDto;
import com.unknown.supportapp.dto.manager.ManagerDto;
import com.unknown.supportapp.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.dto.product.ProductDto;
import com.unknown.supportapp.dto.ticket.TicketDto;
import com.unknown.supportapp.entities.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceUtil;
import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {

    private PersistenceUtil persistenceUtil;

    private ModelMapper modelMapper;


    @Autowired
    public Converter(PersistenceUtil persistenceUtil, ModelMapper modelMapper) {
        this.persistenceUtil = persistenceUtil;
        this.modelMapper = modelMapper;
    }

    public AccountDto convertAccountToDto(Account entity) {
        TypeMap<Account, AccountDto> typeMap = modelMapper.typeMap(Account.class, AccountDto.class);

        if (!persistenceUtil.isLoaded(entity, "ownedProducts")) {
            typeMap.addMappings(mapping -> mapping.skip(AccountDto::setOwnedProducts));
        }

        if (!persistenceUtil.isLoaded(entity, "tickets")) {
            typeMap.addMappings(mapping -> mapping.skip(AccountDto::setTickets));
        }
        AccountDto accountDto = modelMapper.map(entity, AccountDto.class);
        typeMap.addMappings(mapping -> mapping.map(Account::getOwnedProducts, AccountDto::setOwnedProducts));
        typeMap.addMappings(mapping -> mapping.map(Account::getTickets, AccountDto::setTickets));

        return accountDto;
    }

    public List<AccountDto> convertAccountToDto(List<Account> entityList) {
        List<AccountDto> dtos = new ArrayList<>();
        for (Account entity : entityList) {
            dtos.add(convertAccountToDto(entity));
        }
        return dtos;
    }

    public Account convertAccountToEntity(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }

    public Manager convertManagerToEntity(ManagerDto dto) {
        return modelMapper.map(dto, Manager.class);
    }

    public ManagerDto convertManagerToDto(Manager entity) {
        TypeMap<Manager, ManagerDto> typeMap = modelMapper.typeMap(Manager.class, ManagerDto.class);

        if (!persistenceUtil.isLoaded(entity, "managedTickets")) {
            typeMap.addMappings(mapping -> mapping.skip(ManagerDto::setManagedTickets));
        }
        ManagerDto managerDto = modelMapper.map(entity, ManagerDto.class);
        typeMap.addMappings(mapping -> mapping.map(Manager::getManagedTickets, ManagerDto::setManagedTickets));
        return managerDto;
    }

    public OwnedProduct convertOwnedProductToEntity(OwnedProductDto dto) {
        return modelMapper.map(dto, OwnedProduct.class);
    }

    public OwnedProductDto convertOwnedProductToDto(OwnedProduct entity) {
        return modelMapper.map(entity, OwnedProductDto.class);
    }

    public List<OwnedProductDto> convertOwnedProductToDto(List<OwnedProduct> entityList) {
        List<OwnedProductDto> dtoList = new ArrayList<>();

        for (OwnedProduct entity : entityList) {
            dtoList.add(convertOwnedProductToDto(entity));
        }
        return dtoList;
    }

    public List<OwnedProduct> convertOwnedProductToEntity(List<OwnedProductDto> dtoList) {
        List<OwnedProduct> entityList = new ArrayList<>();

        for (OwnedProductDto dto : dtoList) {
            entityList.add(convertOwnedProductToEntity(dto));
        }
        return entityList;
    }

    public ProductDto convertProductToDto(Product entity) {
        return modelMapper.map(entity, ProductDto.class);
    }

    public List<ProductDto> convertProductToDto(List<Product> entityList) {
        List<ProductDto> dtoList = new ArrayList<>();

        for (Product entity : entityList) {
            ProductDto dto = convertProductToDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public TicketDto convertTicketToDto(Ticket entity) {
       return modelMapper.map(entity, TicketDto.class);
    }

    public List<TicketDto> convertTicketToDto(List<Ticket> entityList) {
        ArrayList<TicketDto> dtoList = new ArrayList<>();

        for (Ticket entity : entityList) {
            TicketDto dto = convertTicketToDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<Ticket> convertTicketToEntity(List<TicketDto> dtoList) {
        ArrayList<Ticket> entityList = new ArrayList<>();

        for (TicketDto dto : dtoList) {
            Ticket entity = convertTicketToEntity(dto);
            entityList.add(entity);
        }
        return entityList;
    }

    public Ticket convertTicketToEntity(TicketDto dto) {
        return modelMapper.map(dto, Ticket.class);
    }
}
