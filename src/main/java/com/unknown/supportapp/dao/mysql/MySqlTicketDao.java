package com.unknown.supportapp.dao.mysql;

import com.unknown.supportapp.dao.TicketDao;
import com.unknown.supportapp.entities.Ticket;
import com.unknown.supportapp.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MySqlTicketDao implements TicketDao {
    @PersistenceContext
    private EntityManager entityManager;

    public MySqlTicketDao() {
    }

    public MySqlTicketDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Ticket> loadAll() {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t", Ticket.class);
        return query.getResultList();
    }

    @Override
    public List<Ticket> loadUserTickets(Long userId) {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.userId = :userId", Ticket.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public void save(Ticket ticket) {
        entityManager.persist(ticket);
    }

    @Override
    public void update(Ticket ticket) {
        entityManager.merge(ticket);
    }

    @Override
    public List<Ticket> loadUserOpenedTickets(Long userId) {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.status = true AND t.starterId = :userId", Ticket.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Ticket> loadUserClosedTickets(Long userId) {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.status = false AND t.starterId = :userId", Ticket.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Ticket> loadUnAssignedTickets() {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.managerId = 0", Ticket.class);
        return query.getResultList();
    }

    @Override
    public List<Ticket> loadManagedTickets(Long managerId) {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.managerId = :managerId", Ticket.class);
        query.setParameter("managerId", managerId);
        return query.getResultList();
    }

    @Override
    public void setManagerId(Ticket ticket) {
        update(ticket);
    }

    @Override
    public void closeTicket(Long id) {
        Ticket ticket = entityManager.find(Ticket.class, id);
        if(ticket == null){
            throw new NoSuchEntityException("No ticket with such id");
        }
        ticket.setStatus(false);
        entityManager.merge(ticket);
    }
}
