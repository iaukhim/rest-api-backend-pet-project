package com.unknown.supportapp.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@Entity
@Table(name = "managers_accounts", schema = "pet_db")
public class Manager extends AbstractEntity{

    @Column(unique = true)
    private String email;

    private String password;

    private String role = "ROLE_MANAGER";

    public Manager(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "manager")
    private List<Ticket> managedTickets;

    public void manageTicket(Ticket ticket){
        ticket.setManager(this);
        managedTickets.add(ticket);
    }

}
