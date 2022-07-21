package com.unknown.supportapp.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(callSuper = true)
@Entity
@Table(name = "accounts", schema = "pet_db")
public class Account extends AbstractEntity {

    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String surname;

    private String role = "ROLE_USER";
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OwnedProduct> ownedProducts = new java.util.ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "starter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new java.util.ArrayList<>();

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Account(Long id, String email, String password, String name, String surname, String phoneNumber, LocalDate dateOfBirth) {
        super(id);
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public void addOwnedProduct(OwnedProduct ownedProduct){
        ownedProduct.setOwner(this);
        ownedProducts.add(ownedProduct);
    }

    public void addTicket(Ticket ticket){
        ticket.setStarter(this);
        tickets.add(ticket);
    }


}
