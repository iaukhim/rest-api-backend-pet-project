package com.unknown.supportapp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "owned_products", schema = "pet_db")
public class OwnedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    private String type;

    private String model;

    @Column(name = "serial_number")
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private Account owner;

    public OwnedProduct() {
    }

    public OwnedProduct(Long id, Long ownerId, String type, String model, String serialNumber) {
        this.id = id;
        this.ownerId = ownerId;
        this.type = type;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedProduct that = (OwnedProduct) o;
        return Objects.equals(id, that.id) && Objects.equals(ownerId, that.ownerId) && Objects.equals(type, that.type) && Objects.equals(model, that.model) && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, type, model, serialNumber, owner);
    }

    @Override
    public String toString() {
        return "OwnedProduct{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
