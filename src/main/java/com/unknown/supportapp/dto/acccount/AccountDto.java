package com.unknown.supportapp.dto.acccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.unknown.supportapp.dao.OwnedProductDao;
import com.unknown.supportapp.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.dto.ticket.TicketDto;
import com.unknown.supportapp.entities.OwnedProduct;
import com.unknown.supportapp.entities.Ticket;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {

    private String email;

    private String password;

    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<OwnedProductDto> ownedProducts;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TicketDto> tickets;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    public AccountDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AccountDto(String email, String password, Long id, String name, String surname, String phoneNumber, LocalDate dateOfBirth) {
        this.email = email;
        this.password = password;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public void addOwnedProduct(OwnedProductDto ownedProduct){
        ownedProduct.setOwner(this);
        ownedProducts.add(ownedProduct);
    }

    public void addTicket(TicketDto ticket){
        ticket.setStarter(this);
        tickets.add(ticket);
    }

}


