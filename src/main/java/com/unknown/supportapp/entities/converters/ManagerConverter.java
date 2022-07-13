package com.unknown.supportapp.entities.converters;


import com.unknown.supportapp.dto.manager.ManagerDto;
import com.unknown.supportapp.entities.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerConverter {

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
        return entity;
    }
}
