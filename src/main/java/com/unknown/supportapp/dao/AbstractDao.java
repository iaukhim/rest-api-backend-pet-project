package com.unknown.supportapp.dao;

import com.unknown.supportapp.entities.AbstractEntity;
import com.unknown.supportapp.exceptions.NoSuchEntityException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public interface AbstractDao<T> {

    Class<T> getClazz();

    List<T> loadAll();

    void deleteById(Long id);

    T findById(Long id);
}
