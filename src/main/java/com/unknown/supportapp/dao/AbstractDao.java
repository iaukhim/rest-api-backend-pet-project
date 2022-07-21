package com.unknown.supportapp.dao;

import com.unknown.supportapp.entities.AbstractEntity;

import java.util.List;

public interface AbstractDao {

    List<AbstractEntity> loadAll();

    void deleteById(Long id);

    AbstractEntity findById(Long id);
}
