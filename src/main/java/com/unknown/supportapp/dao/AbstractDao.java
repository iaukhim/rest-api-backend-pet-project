package com.unknown.supportapp.dao;

import java.util.List;

public interface AbstractDao<T> {

    List<T> loadAll();

    void deleteById(Long id);

    T findById(Long id);

    T update (T entity);

    T save (T entity);

}
