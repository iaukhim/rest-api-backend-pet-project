package com.unknown.supportapp.dto.manager;

import com.unknown.supportapp.dto.ticket.TicketDto;
import com.unknown.supportapp.entities.Ticket;
import lombok.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManagerDto {

    private Long id;

    private String email;

    private String password;

    private String role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TicketDto> managedTickets;

    public void manageTicket(TicketDto ticket){
        ticket.setManager(this);
        managedTickets.add(ticket);
    }

}
