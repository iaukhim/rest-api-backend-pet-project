package com.unknown.supportapp.controllers.rest;

import com.unknown.supportapp.config.MailProps;
import com.unknown.supportapp.entities.converters.Converter;
import com.unknown.supportapp.services.AccountService;
import com.unknown.supportapp.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    MailProps mailProps;

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
