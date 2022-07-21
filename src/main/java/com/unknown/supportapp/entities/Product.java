package com.unknown.supportapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
@Entity
@Table(schema = "pet_db", name = "products")
public class Product extends AbstractEntity{

    private String type;

    @Column(unique = true)
    private String model;

}
