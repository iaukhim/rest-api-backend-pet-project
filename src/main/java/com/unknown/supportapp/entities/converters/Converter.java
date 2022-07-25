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

        TypeMap<OwnedProduct, OwnedProductDto> typeMapOwnedProduct = modelMapper.emptyTypeMap(OwnedProduct.class, OwnedProductDto.class);
        typeMapOwnedProduct.addMappings(mapping -> mapping.skip(OwnedProductDto::setOwner)).implicitMappings();

        TypeMap<Ticket, TicketDto> typeMapTickets = modelMapper.emptyTypeMap(Ticket.class, TicketDto.class);
        typeMapTickets.addMappings(mapping -> mapping.skip(TicketDto::setManager));
        typeMapTickets.addMappings(mapping -> mapping.skip(TicketDto::setStarter)).implicitMappings();
    }

    public AccountDto convertAccountToDto(Account entity, boolean skipCollections) {
        TypeMap<Account, AccountDto> typeMap = modelMapper.typeMap(Account.class, AccountDto.class);

        if (!persistenceUtil.isLoaded(entity, "ownedProducts") || skipCollections) {
            typeMap.addMappings(mapping -> mapping.skip(AccountDto::setOwnedProducts));
        }

        if (!persistenceUtil.isLoaded(entity, "tickets") || skipCollections) {
            typeMap.addMappings(mapping -> mapping.skip(AccountDto::setTickets));
        }
        AccountDto accountDto = modelMapper.map(entity, AccountDto.class);
        typeMap.addMappings(mapping -> mapping.map(Account::getOwnedProducts, AccountDto::setOwnedProducts));
        typeMap.addMappings(mapping -> mapping.map(Account::getTickets, AccountDto::setTickets));

        return accountDto;
    }

    public List<AccountDto> convertAccountToDto(List<Account> entityList, boolean skipCollections) {
        List<AccountDto> dtos = new ArrayList<>();
        for (Account entity : entityList) {
            dtos.add(convertAccountToDto(entity, skipCollections));
        }
        return dtos;
    }

    public Account convertAccountToEntity(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }

    public Manager convertManagerToEntity(ManagerDto dto) {
        return modelMapper.map(dto, Manager.class);
    }

    public ManagerDto convertManagerToDto(Manager entity, boolean skipCollections) {
        TypeMap<Manager, ManagerDto> typeMap = modelMapper.typeMap(Manager.class, ManagerDto.class);

        if (!persistenceUtil.isLoaded(entity, "managedTickets") || skipCollections) {
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
        OwnedProductDto dto = new OwnedProductDto();
        modelMapper.map(entity, dto);
        dto.setOwner(convertAccountToDto(entity.getOwner(), true));
        return dto;
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
        TicketDto ticketDto = modelMapper.map(entity, TicketDto.class);
        ticketDto.setStarter(convertAccountToDto(entity.getStarter(), true));
        ticketDto.setManager(convertManagerToDto(entity.getManager(), true));
        return ticketDto;
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
