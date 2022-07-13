package com.unknown.supportapp.services.impl;

import com.unknown.supportapp.dao.AccountDao;
import com.unknown.supportapp.dao.TicketDao;
import com.unknown.supportapp.dto.ticket.TicketDto;
import com.unknown.supportapp.entities.Ticket;
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

    private TicketConverter ticketConverter;

    public TicketServiceImpl() {
    }

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao, AccountDao accountDao, TicketConverter ticketConverter) {
        this.ticketDao = ticketDao;
        this.accountDao = accountDao;
        this.ticketConverter = ticketConverter;
    }

    @Override
    public List<TicketDto> loadUserTickets(Long userId) {
        List<Ticket> tickets = ticketDao.loadUserTickets(userId);

        List<TicketDto> ticketDtos = ticketConverter.convertToDtoList(tickets);
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
        List<TicketDto> ticketDtos = ticketConverter.convertToDtoList(entityList);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadUserClosedTickets(Long userId) {
        List<Ticket> entityList = ticketDao.loadUserClosedTickets(userId);
        List<TicketDto> ticketDtos = ticketConverter.convertToDtoList(entityList);
        return ticketDtos;
    }

    @Override
    public void setManagerId(TicketDto ticketDto) {
        Ticket ticket = ticketConverter.convertToEntity(ticketDto);
        ticketDao.setManagerId(ticket);
    }

    @Override
    public List<TicketDto> loadUnAssignedTickets() {
        List<Ticket> tickets = ticketDao.loadUnAssignedTickets();
        List<TicketDto> ticketDtos = ticketConverter.convertToDtoList(tickets);
        return ticketDtos;
    }

    @Override
    public List<TicketDto> loadManagedTickets(Long managerId) {
        List<Ticket> entities = ticketDao.loadManagedTickets(managerId);
        List<TicketDto> ticketDtos = ticketConverter.convertToDtoList(entities);
        return ticketDtos;
    }

    @Override
    public void save(TicketDto ticket) {
        Ticket ticketEntity = ticketConverter.convertToEntity(ticket);
        ticketDao.save(ticketEntity);
    }

    @Override
    public void update(TicketDto ticketDto) {
        Ticket ticket = ticketConverter.convertToEntity(ticketDto);
        ticketDao.update(ticket);
    }

    @Override
    public void closeTicket(Long id) {
        ticketDao.closeTicket(id);
    }
}
