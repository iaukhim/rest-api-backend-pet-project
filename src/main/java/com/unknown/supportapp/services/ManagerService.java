package com.unknown.supportapp.services;


import com.unknown.supportapp.dto.manager.ManagerDto;
import com.unknown.supportapp.entities.Manager;

public interface ManagerService {

    Long loadIdByEmail(String email);

    void save(Manager manager);
}
