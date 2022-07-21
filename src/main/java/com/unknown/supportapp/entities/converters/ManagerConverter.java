package com.unknown.supportapp.entities.converters;


import com.unknown.supportapp.dto.manager.ManagerDto;
import com.unknown.supportapp.entities.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceUtil;

@Component
public class ManagerConverter {

    @Autowired
    PersistenceUtil persistenceUtil;

    TicketConverter ticketConverter = new TicketConverter();
    public Manager convertToEntity(ManagerDto dto){
        Manager entity = new Manager();

        if(dto.getId() != null){
            entity.setId(dto.getId());
        }

        if(dto.getEmail() != null){
            entity.setEmail(dto.getEmail());
        }
        if(dto.getPassword() != null){
            entity.setPassword(dto.getPassword());
        }

        if(dto.getManagedTickets() != null){
            entity.setManagedTickets(ticketConverter.convertToEntityList(dto.getManagedTickets()));
        }
        return entity;
    }

    public ManagerDto convertToDto(Manager entity){
        ManagerDto dto = new ManagerDto();

        if(entity.getId() != null){
            dto.setId(entity.getId());
        }

        if(entity.getEmail() != null){
            dto.setEmail(entity.getEmail());
        }
        if(entity.getPassword() != null){
            dto.setPassword(entity.getPassword());
        }
        if(persistenceUtil.isLoaded(entity,"managedTickets")){
            dto.setManagedTickets(ticketConverter.convertToDtoList(entity.getManagedTickets()));
        }
        return dto;
    }
}
