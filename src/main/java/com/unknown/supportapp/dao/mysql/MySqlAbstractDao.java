package com.unknown.supportapp.dao.mysql;

import com.unknown.supportapp.dao.AbstractDao;
import com.unknown.supportapp.entities.AbstractEntity;
import com.unknown.supportapp.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public abstract class MySqlAbstractDao<T> implements AbstractDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Class<T> getClazz() {
        return null;
    }

    @Override
    public List<T> loadAll() {
        CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(getClazz());
        Root from = query.from(getClazz());
        query.select(from);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public T findById(Long id) {
        try {
            return entityManager.find(getClazz(), id);
        } catch (Exception e) {
            throw new NoSuchEntityException(e);
        }
    }
}

