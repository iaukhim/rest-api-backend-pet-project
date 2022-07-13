package com.unknown.supportapp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "pet_db", name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "starter_id")
    private Long starterId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "product_id")
    private Long productId;

    private String theme;

    private String text;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "starter_id", insertable = false, updatable = false)
    private Account starter;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", insertable = false, updatable = false)
    private Manager manager;

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStarterId() {
        return starterId;
    }

    public void setStarterId(Long starterId) {
        this.starterId = starterId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", starterId=" + starterId +
                ", managerId=" + managerId +
                ", productId=" + productId +
                ", theme='" + theme + '\'' +
                ", text='" + text + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return status == ticket.status && Objects.equals(id, ticket.id) && Objects.equals(starterId, ticket.starterId) && Objects.equals(managerId, ticket.managerId) && Objects.equals(productId, ticket.productId) && Objects.equals(theme, ticket.theme) && Objects.equals(text, ticket.text) && Objects.equals(starter, ticket.starter) && Objects.equals(manager, ticket.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, starterId, managerId, productId, theme, text, status, starter, manager);
    }
}
