package com.unknown.supportapp.entities;

import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
@Entity
@Table(schema = "pet_db", name = "tickets")
public class Ticket extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "owned_product_id")
    private OwnedProduct ownedProduct;

    private String theme;

    private String text;

    private boolean status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne()
    @JoinColumn(name = "starter_id")
    private Account starter;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne()
    @JoinColumn(name = "manager_id")
    private Manager manager;


}
