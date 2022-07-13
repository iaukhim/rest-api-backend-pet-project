package com.unknown.supportapp.dto.ownedProduct;

import java.util.Objects;

public class OwnedProductDto {

    private Long id;

    private Long ownerId;

    private String type;

    private String model;

    private String serialNumber;


    public OwnedProductDto() {
    }

    public OwnedProductDto(Long id, Long ownerId, String type, String model, String serialNumber) {
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

    @Override
    public String toString() {
        return "OwnedProductDto{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedProductDto that = (OwnedProductDto) o;
        return Objects.equals(id, that.id) && Objects.equals(ownerId, that.ownerId) && Objects.equals(type, that.type) && Objects.equals(model, that.model) && Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, type, model, serialNumber);
    }
}
