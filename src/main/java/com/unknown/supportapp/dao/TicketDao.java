package com.unknown.supportapp.dao;

import com.unknown.supportapp.entities.Ticket;

import java.util.List;

public interface TicketDao {

    List<Ticket> loadAll();

    List<Ticket> loadUserTickets(Long userId);

    void save(Ticket ticket);

    void update(Ticket ticket);

    List<Ticket> loadUserOpenedTickets(Long userId);

    List<Ticket> loadUserClosedTickets(Long userId);

    List<Ticket> loadUnAssignedTickets();

    List<Ticket> loadManagedTickets(Long managerId);

    void setManagerId(Ticket ticket);

    void closeTicket(Long id);
}

