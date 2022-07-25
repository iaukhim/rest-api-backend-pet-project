package com.unknown.supportapp.dao;

import com.unknown.supportapp.entities.Manager;

public interface ManagerDao extends AbstractDao<Manager> {

    boolean login(Manager manager);

    Long loadIdByEmail(String email);

    Manager loadByEmail(String email);

}
