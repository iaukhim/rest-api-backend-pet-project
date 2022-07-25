package com.unknown.supportapp.dao;

import com.unknown.supportapp.entities.Ticket;

import java.util.List;

public interface TicketDao extends AbstractDao<Ticket> {

    List<Ticket> loadUserTickets(Long userId);

    List<Ticket> loadUserOpenedTickets(Long userId);

    List<Ticket> loadUserClosedTickets(Long userId);

    List<Ticket> loadUnAssignedTickets();

    List<Ticket> loadManagedTickets(Long managerId);

    void setManagerId(Ticket ticket);

    void closeTicket(Long id);
}

