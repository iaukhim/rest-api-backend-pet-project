package com.unknown.supportapp.services;

import com.unknown.supportapp.dto.ticket.TicketDto;

import java.util.List;

public interface TicketService {

    List<TicketDto> loadUserTickets(Long userId);

    List<TicketDto> loadUserTickets(String email);

    List<TicketDto> loadUserOpenedTickets(Long userId);

    List<TicketDto> loadUserClosedTickets(Long userId);

    List<TicketDto> loadUnAssignedTickets();

    List<TicketDto> loadManagedTickets(Long managerId);

    void save(TicketDto ticket);

    void update(TicketDto ticketDto);

    void closeTicket(Long id);

    void setManagerId(TicketDto ticketDto);

}
