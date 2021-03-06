package com.unknown.supportapp.dao.mysql;


import com.unknown.supportapp.dao.AccountDao;
import com.unknown.supportapp.entities.Account;
import com.unknown.supportapp.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class MySqlAccountDao extends MySqlAbstractDao<Account> implements AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    public MySqlAccountDao() {

    }


    @Override
    public Class<Account> getClazz() {
        return Account.class;
    }

    @Override
    public boolean logIn(Account account) {
        boolean result = true;

        Query query = entityManager.createQuery("select a  from Account as a WHERE a.email = :email and a.password = :password", Account.class);
        query.setParameter("email", account.getEmail());
        query.setParameter("password", account.getPassword());

        if (query.getResultList().isEmpty()) {
            result = false;
        }

        return result;
    }

    @Override
    public Account loadByEmail(String email) {
        TypedQuery<Account> query = entityManager.createQuery("select a from Account as a where a.email = :email", Account.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchEntityException("Entity with such email does not exist");
        }
    }
    @Override
    public void save(Account account) {
        entityManager.persist(account);
    }

    @Override
    public boolean checkAccountExistence(String email) {
        boolean result = true;

        TypedQuery<Account> query = entityManager.createQuery("select a from Account as a where a.email = :email", Account.class);
        query.setParameter("email", email);
        if (query.getResultList().isEmpty()) {
            result = false;
        }
        return result;
    }

    @Override
    public void changePassword(Account account) {
        Account storedAccount = entityManager.find(Account.class, account.getId());
        storedAccount.setPassword(account.getPassword());
        entityManager.merge(storedAccount);
    }

    @Override
    public Long loadIdByEmail(String email) {
        Long id;
        Account account = loadByEmail(email);
        return account.getId();
    }

    @Override
    public void update(Account account) {
        entityManager.merge(account);
    }
}
