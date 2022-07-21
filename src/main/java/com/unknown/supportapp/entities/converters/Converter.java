package com.unknown.supportapp.entities.converters;

import com.unknown.supportapp.dto.acccount.AccountDto;
import com.unknown.supportapp.dto.manager.ManagerDto;
import com.unknown.supportapp.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.dto.product.ProductDto;
import com.unknown.supportapp.dto.ticket.TicketDto;
import com.unknown.supportapp.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceUtil;
import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {

    @Autowired
    private PersistenceUtil persistenceUtil;

    public AccountDto convertAccountToDto(Account entity) {
        AccountDto accountDto = new AccountDto();

        if (entity.getId() != null) {
            accountDto.setId(entity.getId());
        }

        if (entity.getEmail() != null) {
            accountDto.setEmail(entity.getEmail());
        }
        if (entity.getPassword() != null) {
            accountDto.setPassword(entity.getPassword());
        }
        if (entity.getName() != null) {
            accountDto.setName(entity.getName());
        }
        if (entity.getSurname() != null) {
            accountDto.setSurname(entity.getSurname());
        }
        if (entity.getPhoneNumber() != null) {
            accountDto.setPhoneNumber(entity.getPhoneNumber());
        }
        if (entity.getDateOfBirth() != null) {
            accountDto.setDateOfBirth(entity.getDateOfBirth());
        }


      /*  if (persistenceUtil.isLoaded(entity, "ownedProducts")) {
            accountDto.setOwnedProducts(convertOwnedProductToDto(entity.getOwnedProducts()));
        }

        if (persistenceUtil.isLoaded(entity, "tickets")) {
            accountDto.setTickets(convertTicketToDto(entity.getTickets()));
        }*/

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
        Account accountEntity = new Account();

        if (accountDto.getId() != null) {
            accountEntity.setId(accountDto.getId());
        }

        if (accountDto.getEmail() != null) {
            accountEntity.setEmail(accountDto.getEmail());
        }
        if (accountDto.getPassword() != null) {
            accountEntity.setPassword(accountDto.getPassword());
        }
        if (accountDto.getName() != null) {
            accountEntity.setName(accountDto.getName());
        }
        if (accountDto.getSurname() != null) {
            accountEntity.setSurname(accountDto.getSurname());
        }
        if (accountDto.getPhoneNumber() != null) {
            accountEntity.setPhoneNumber(accountDto.getPhoneNumber());
        }
        if (accountDto.getDateOfBirth() != null) {
            accountEntity.setDateOfBirth(accountDto.getDateOfBirth());
        }

        if (accountDto.getOwnedProducts() != null) {
            accountEntity.setOwnedProducts(convertOwnedProductToEntity(accountDto.getOwnedProducts()));
        }


        if (accountDto.getTickets() != null) {
            accountEntity.setTickets(convertTicketToEntity(accountDto.getTickets()));
        }
        return accountEntity;
    }

    public Manager convertManagerToEntity(ManagerDto dto) {
        Manager entity = new Manager();

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }

        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            entity.setPassword(dto.getPassword());
        }

        if (dto.getManagedTickets() != null) {
            entity.setManagedTickets(convertTicketToEntity(dto.getManagedTickets()));
        }
        return entity;
    }

    public ManagerDto convertManagerToDto(Manager entity) {
        ManagerDto dto = new ManagerDto();

        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }

        if (entity.getEmail() != null) {
            dto.setEmail(entity.getEmail());
        }
        if (entity.getPassword() != null) {
            dto.setPassword(entity.getPassword());
        }
        /*if (persistenceUtil.isLoaded(entity, "managedTickets")) {
            dto.setManagedTickets(convertTicketToDto(entity.getManagedTickets()));
        }*/
        return dto;
    }

    public OwnedProduct convertOwnedProductToEntity(OwnedProductDto dto) {
        OwnedProduct ownedProduct = new OwnedProduct();

        if (dto.getId() != null) {
            ownedProduct.setId(dto.getId());
        }
/*
        if (dto.getOwnerId() != null) {
            ownedProduct.setOwnerId(dto.getOwnerId());
        }

        if (dto.getType() != null) {
            ownedProduct.setType(dto.getType());
        }
        if (dto.getModel() != null) {
            ownedProduct.setModel(dto.getModel());
        }
        if (dto.getSerialNumber() != null) {
            ownedProduct.setSerialNumber(dto.getSerialNumber());
        }*/

        if (dto.getOwner() != null) {
            ownedProduct.setOwner(convertAccountToEntity(dto.getOwner()));
        }

        return ownedProduct;
    }

    public OwnedProductDto convertOwnedProductToDto(OwnedProduct entity) {
        OwnedProductDto dto = new OwnedProductDto();

        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }

        /*if (entity.getOwnerId() != null) {
            dto.setOwnerId(entity.getOwnerId());
        }

        if (entity.getType() != null) {
            dto.setType(entity.getType());
        }
        if (entity.getModel() != null) {
            dto.setModel(entity.getModel());
        }
        if (entity.getSerialNumber() != null) {
            dto.setSerialNumber(entity.getSerialNumber());
        }*/

       /* if (persistenceUtil.isLoaded(entity, "owner")) {
            dto.setOwner(convertAccountToDto(entity.getOwner()));
        }*/

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
        ProductDto productDto = new ProductDto();

        if (entity.getId() != null) {
            productDto.setId(entity.getId());
        }

        if (entity.getType() != null) {
            productDto.setType(entity.getType());
        }
        if (entity.getModel() != null) {
            productDto.setModel(entity.getModel());
        }
        return productDto;
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
        TicketDto dto = new TicketDto();

        dto.setStatus(entity.isStatus());

        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
/*
        if (entity.getStarterId() != null) {
            dto.setStarterId(entity.getStarterId());
        }

        if (entity.getManagerId() != null) {
            dto.setManagerId(entity.getManagerId());
        }

        if (entity.getProductId() != null) {
            dto.setProductId(entity.getProductId());
        }*/

        if (entity.getTheme() != null) {
            dto.setTheme(entity.getTheme());
        }
        if (entity.getText() != null) {
            dto.setText(entity.getText());
        }

       /* if (persistenceUtil.isLoaded(entity, "starter")) {
            dto.setStarter(convertAccountToDto(entity.getStarter()));
        }

        if (persistenceUtil.isLoaded(entity, "manager")) {
            dto.setManager(convertManagerToDto(entity.getManager()));
        }*/
        return dto;
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
        Ticket entity = new Ticket();

        entity.setStatus(dto.isStatus());

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }

       /* if (dto.getStarterId() != null) {
            entity.setStarterId(dto.getStarterId());
        }

        if (dto.getManagerId() != null) {
            entity.setManagerId(dto.getManagerId());
        }

        if (dto.getProductId() != null) {
            entity.setProductId(dto.getProductId());
        }*/

        if (dto.getTheme() != null) {
            entity.setTheme(dto.getTheme());
        }
        if (dto.getText() != null) {
            entity.setText(dto.getText());
        }

        if (dto.getStarter() != null) {
            entity.setStarter(convertAccountToEntity(dto.getStarter()));
        }
        if (dto.getManager() != null) {
            entity.setManager(convertManagerToEntity(dto.getManager()));
        }

        return entity;
    }
}
