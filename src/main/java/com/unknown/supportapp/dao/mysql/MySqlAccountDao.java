package com.unknown.supportapp.dao.mysql;


import com.unknown.supportapp.dao.AccountDao;
import com.unknown.supportapp.entities.Account;
import com.unknown.supportapp.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = cb.createQuery(Account.class);
        Root<Account> accountRoot = criteriaQuery.from(Account.class);
        TypedQuery<Account> accountTypedQuery = entityManager.createQuery(criteriaQuery.select(accountRoot)
                .where(cb.equal
                        (accountRoot.get("email"),
                                cb.parameter(String.class, "email")
                        ), cb.and(
                                cb.equal(accountRoot.get("password"),
                                        cb.parameter(String.class, "password")
                                )
                        )
                )
        ).setParameter("email", account.getEmail()).setParameter("password", account.getPassword());

        if (accountTypedQuery.getResultList().isEmpty()) {
            result = false;
        }

        return result;
    }

    @Override
    public Account loadByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = cb.createQuery(Account.class);
        Root<Account> accountRoot = criteriaQuery.from(Account.class);
        TypedQuery<Account> typedQuery = entityManager.createQuery(criteriaQuery.select(accountRoot)
                .where(cb.equal
                                (accountRoot.get("email"),
                                        cb.parameter(String.class, "email")
                                )
                )
        ).setParameter("email", email);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchEntityException("Entity with such email does not exist");
        }
    }

    @Override
    public boolean checkAccountExistence(String email) {
        boolean result = true;

        try {
            loadByEmail(email);
        }
        catch (NoSuchEntityException e){
            return false;
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
}
