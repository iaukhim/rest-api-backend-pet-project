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
public class MySqlTicketDao extends MySqlAbstractDao<Ticket> implements TicketDao {
    @PersistenceContext
    private EntityManager entityManager;

    public MySqlTicketDao() {
    }

    @Override
    Class<Ticket> getClazz() {
        return Ticket.class;
    }

    @Override
    public List<Ticket> loadUserTickets(Long userId) {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.starter.id = :userId", Ticket.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public List<Ticket> loadUserOpenedTickets(Long userId) {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.status = true AND t.starter.id = :userId", Ticket.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Ticket> loadUserClosedTickets(Long userId) {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.status = false AND t.starter.id = :userId", Ticket.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Ticket> loadUnAssignedTickets() {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.manager is null", Ticket.class);
        return query.getResultList();
    }

    @Override
    public List<Ticket> loadManagedTickets(Long managerId) {
        TypedQuery<Ticket> query = entityManager.createQuery("SELECT t FROM Ticket as t WHERE t.manager.id = :managerId", Ticket.class);
        query.setParameter("managerId", managerId);
        return query.getResultList();
    }

    @Override
    public void setManagerId(Ticket ticket) {
        update(ticket);
    }

    @Override
    public void closeTicket(Long id) {
        Ticket ticket = findById(id);
        if(ticket == null){
            throw new NoSuchEntityException("No ticket with such id");
        }
        ticket.setStatus(false);
        entityManager.merge(ticket);
    }
}
