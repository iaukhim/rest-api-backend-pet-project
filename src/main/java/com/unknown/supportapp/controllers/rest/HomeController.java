package com.unknown.supportapp.controllers.rest;

import com.unknown.supportapp.dao.ManagerDao;
import com.unknown.supportapp.entities.Account;
import com.unknown.supportapp.entities.Manager;
import com.unknown.supportapp.entities.converters.Converter;
import com.unknown.supportapp.services.AccountService;
import com.unknown.supportapp.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private Converter converter;

    @GetMapping("/")
    public String homeController(){
        return "home";
    }
}
