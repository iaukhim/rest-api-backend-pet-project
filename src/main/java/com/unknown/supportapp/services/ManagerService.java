package com.unknown.supportapp.services;


import com.unknown.supportapp.dto.manager.ManagerDto;

public interface ManagerService {

    boolean login (ManagerDto managerDto);

    Long loadIdByEmail(String email);
}
