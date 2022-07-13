package com.unknown.supportapp.services.impl;


import com.unknown.supportapp.dao.ManagerDao;
import com.unknown.supportapp.dto.manager.ManagerDto;
import com.unknown.supportapp.entities.Manager;
import com.unknown.supportapp.entities.converters.ManagerConverter;
import com.unknown.supportapp.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    private ManagerDao managerDao;
    private ManagerConverter managerConverter;

    public ManagerServiceImpl() {
    }

    @Autowired
    public ManagerServiceImpl(ManagerDao managerDao, ManagerConverter managerConverter) {
        this.managerDao = managerDao;
        this.managerConverter = managerConverter;
    }

    @Override
    public boolean login(ManagerDto managerDto) {
        Manager manager = managerConverter.convertToEntity(managerDto);

        boolean result = managerDao.login(manager);

        return result;
    }

    @Override
    public Long loadIdByEmail(String email) {
        Long id = managerDao.loadIdByEmail(email);
        return id;
    }
}
