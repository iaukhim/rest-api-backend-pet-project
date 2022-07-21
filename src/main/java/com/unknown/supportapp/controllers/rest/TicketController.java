package com.unknown.supportapp.controllers.rest;


import com.unknown.supportapp.dto.ticket.TicketDto;
import com.unknown.supportapp.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PutMapping("")
    public void update(@RequestBody TicketDto ticketDto){
        ticketService.update(ticketDto);
    }

    @PostMapping("")
    public void saveTicket(@RequestBody TicketDto ticketDto){
        ticketService.save(ticketDto);
    }

    @GetMapping("/{starterId}/")
    public List<TicketDto> loadUserTickets(@PathVariable Long userId){
        return ticketService.loadUserTickets(userId);
    }

    @GetMapping("/{starterId}/{status}")
    public List<TicketDto> loadTicketsByStatus(@PathVariable boolean status, @PathVariable Long starterId){
        if(status){
            return ticketService.loadUserOpenedTickets(starterId);
        }
        return ticketService.loadUserClosedTickets(starterId);
    }

    @GetMapping("manager-id/0")
    public List<TicketDto> loadUnAssignedTickets(){
        return ticketService.loadUnAssignedTickets();
    }
}
