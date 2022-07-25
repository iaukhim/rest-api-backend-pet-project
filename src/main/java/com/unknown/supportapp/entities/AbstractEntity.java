package com.unknown.supportapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class AbstractEntity {

    public AbstractEntity(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
}
