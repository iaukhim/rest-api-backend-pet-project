package com.unknown.supportapp.dao.mysql;


import com.unknown.supportapp.dao.ManagerDao;
import com.unknown.supportapp.entities.Manager;
import com.unknown.supportapp.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class MySqlManagerDao implements ManagerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public MySqlManagerDao() {
    }

    public MySqlManagerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean login(Manager manager) {
        boolean result = true;

        Query query = entityManager.createQuery("select m  from Manager as m WHERE m.email = :email and m.password = :password", Manager.class);
        query.setParameter("email", manager.getEmail());
        query.setParameter("password", manager.getPassword());
        if (query.getResultList().isEmpty()) {
            result = false;
        }

        return result;
    }

    @Override
    public Manager save(Manager manager) {
        return entityManager.merge(manager);
    }

    @Override
    public Long loadIdByEmail(String email) {
        Long id;

        TypedQuery<Long> query = entityManager.createQuery("select m.id from Manager as m WHERE m.email = :email", Long.class);
        query.setParameter("email", email);
        try {
            id = query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchEntityException("Entity with email does not exist");
        }
        return id;
    }

    @Override
    public Manager loadByEmail(String email) {
        TypedQuery<Manager> query = entityManager.createQuery("select m from Manager as m WHERE m.email = :email", Manager.class);
        query.setParameter("email", email);
        Manager manager;
        try {
            manager = query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchEntityException("Entity with such email does not exist");
        }
        return manager;
    }
}
