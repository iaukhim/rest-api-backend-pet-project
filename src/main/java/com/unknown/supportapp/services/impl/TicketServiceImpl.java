package com.unknown.supportapp.services.impl;

import com.unknown.supportapp.dao.AccountDao;
import com.unknown.supportapp.dao.TicketDao;
import com.unknown.supportapp.dto.ticket.TicketDto;
import com.unknown.supportapp.entities.Ticket;
import com.unknown.supportapp.entities.converters.Converter;
import com.unknown.supportapp.entities.converters.TicketConverter;
import com.unknown.supportapp.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao;

    private AccountDao accountDao;

    private Converter converter;

    public TicketServiceImpl() {
    }

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao, AccountDao accountDao, Converter converter) {
        this.ticketDao = ticketDao;
        this.accountDao = accountDao;
        this.converter = converter;
    }

    @Override
    public List<TicketDto> loadUserTickets(Long userId) {
        List<Ticket> tickets = ticketDao.loadUserTickets(userId);

        List<TicketDto> ticketDtos = converter.convertTicketToDto(tickets);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserTickets(String email) {
        Long id = accountDao.loadIdByEmail(email);
        List<TicketDto> ticketDtos = loadUserTickets(id);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserOpenedTickets(Long userId) {
        List<Ticket> entityList = ticketDao.loadUserOpenedTickets(userId);
        List<TicketDto> ticketDtos = converter.convertTicketToDto(entityList);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserClosedTickets(Long userId) {
        List<Ticket> entityList = ticketDao.loadUserClosedTickets(userId);
        List<TicketDto> ticketDtos = converter.convertTicketToDto(entityList);
        return ticketDtos;
    }

    @Override
    public void setManagerId(TicketDto ticketDto) {
        Ticket ticket = converter.convertTicketToEntity(ticketDto);
        ticketDao.setManagerId(ticket);
    }

    @Override
    public List<TicketDto> loadUnAssignedTickets() {
        List<Ticket> tickets = ticketDao.loadUnAssignedTickets();
        List<TicketDto> ticketDtos = converter.convertTicketToDto(tickets);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadManagedTickets(Long managerId) {
        List<Ticket> entities = ticketDao.loadManagedTickets(managerId);
        List<TicketDto> ticketDtos = converter.convertTicketToDto(entities);
        return ticketDtos;
    }

    @Override
    public void save(TicketDto ticket) {
        Ticket ticketEntity = converter.convertTicketToEntity(ticket);
        ticketDao.save(ticketEntity);
    }

    @Override
    public void update(TicketDto ticketDto) {
        Ticket ticket = converter.convertTicketToEntity(ticketDto);
        ticketDao.update(ticket);
    }

    @Override
    public void closeTicket(Long id) {
        ticketDao.closeTicket(id);
    }
}
