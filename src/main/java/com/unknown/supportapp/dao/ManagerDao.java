package com.unknown.supportapp.dao;

import com.unknown.supportapp.entities.Manager;

public interface ManagerDao {

    boolean login(Manager manager);

    Long loadIdByEmail(String email);

    Manager loadByEmail(String email);

    Manager save(Manager manager);
}
