package com.unknown.supportapp.dto.ticket;

import com.unknown.supportapp.dto.acccount.AccountDto;
import com.unknown.supportapp.dto.manager.ManagerDto;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {

    private Long id;

    private Long starterId;

    private Long managerId;

    private Long productId;

    private String theme;

    private String text;

    private boolean status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "starter_id", insertable = false, updatable = false)
    private AccountDto starter;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", insertable = false, updatable = false)
    private ManagerDto manager;
}
