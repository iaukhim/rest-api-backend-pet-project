package com.unknown.supportapp.dto.manager;

import com.unknown.supportapp.dto.ticket.TicketDto;
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TicketDto> managedTickets;

}
