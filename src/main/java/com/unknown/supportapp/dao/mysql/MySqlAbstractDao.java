package com.unknown.supportapp.dao.mysql;

import com.unknown.supportapp.dao.AbstractDao;
import com.unknown.supportapp.entities.AbstractEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MySqlAbstractDao implements AbstractDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AbstractEntity> loadAll() {
        List<AbstractEntity> from_abstractEntity = entityManager.createQuery("FROM AbstractEntity", AbstractEntity.class).getResultList();
        return from_abstractEntity;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public AbstractEntity findById(Long id) {
        return entityManager.find(AbstractEntity.class, id);
    }
}
