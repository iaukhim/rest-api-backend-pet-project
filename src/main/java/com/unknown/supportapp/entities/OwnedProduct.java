package com.unknown.supportapp.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
@Entity
@Table(name = "owned_products", schema = "pet_db")
public class OwnedProduct extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private Account owner;


}
