package com.unknown.supportapp.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "managers_accounts", schema = "pet_db")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "manager")
    private List<Ticket> managedTickets;

    public Manager() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ticket> getManagedTickets() {
        return managedTickets;
    }

    public void setManagedTickets(List<Ticket> managedTickets) {
        this.managedTickets = managedTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id) && Objects.equals(email, manager.email) && Objects.equals(password, manager.password) && Objects.equals(managedTickets, manager.managedTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, managedTickets);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
