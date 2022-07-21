package com.unknown.supportapp.entities.converters;

import com.unknown.supportapp.dto.ticket.TicketDto;
import com.unknown.supportapp.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceUtil;
import java.util.ArrayList;
import java.util.List;

@Component
public class TicketConverter {

    @Autowired
    private PersistenceUtil persistenceUtil;

    @Autowired
    private AccountConverter accountConverter;

    @Autowired
    private ManagerConverter managerConverter;

    public TicketDto convertToDto(Ticket entity){
        TicketDto dto = new TicketDto();

        dto.setStatus(entity.isStatus());

        if(entity.getId() != null){
            dto.setId(entity.getId());
        }

       /* if(entity.getStarterId() != null){
            dto.setStarterId(entity.getStarterId());
        }

        if(entity.getManagerId() != null){
            dto.setManagerId(entity.getManagerId());
        }

        if(entity.getProductId() != null){
            dto.setProductId(entity.getProductId());
        }
*/
        if(entity.getTheme() != null){
            dto.setTheme(entity.getTheme());
        }
        if(entity.getText() != null){
            dto.setText(entity.getText());
        }

        if(persistenceUtil.isLoaded(entity, "starter")){
            dto.setStarter(accountConverter.convertToDto(entity.getStarter()));
        }

        if(persistenceUtil.isLoaded(entity, "manager")){
            dto.setManager(managerConverter.convertToDto(entity.getManager()));
        }
        return dto;
    }

    public List<TicketDto> convertToDtoList(List<Ticket> entityList){
        ArrayList<TicketDto> dtoList = new ArrayList<>();

        for (Ticket entity: entityList) {
            TicketDto dto = convertToDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<Ticket> convertToEntityList(List<TicketDto> dtoList){
        ArrayList<Ticket> entityList = new ArrayList<>();

        for (TicketDto dto: dtoList) {
            Ticket entity = convertToEntity(dto);
            entityList.add(entity);
        }
        return entityList;
    }

    public Ticket convertToEntity(TicketDto dto){
        Ticket entity = new Ticket();

        entity.setStatus(dto.isStatus());

        if(dto.getId() != null){
            entity.setId(dto.getId());
        }

       /* if(dto.getStarterId() != null){
            entity.setStarterId(dto.getStarterId());
        }

        if(dto.getManagerId() != null){
            entity.setManagerId(dto.getManagerId());
        }

        if(dto.getProductId() != null){
            entity.setProductId(dto.getProductId());
        }*/

        if(dto.getTheme() != null){
            entity.setTheme(dto.getTheme());
        }
        if(dto.getText() != null){
            entity.setText(dto.getText());
        }

        if(dto.getStarter() != null) {
            entity.setStarter(accountConverter.convertToEntity(dto.getStarter()));
        }
        if(dto.getManager() != null){
            entity.setManager(managerConverter.convertToEntity(dto.getManager()));
        }

        return entity;
    }
}
