package com.unknown.supportapp.controllers.rest;

import com.unknown.supportapp.dto.manager.ManagerDto;
import com.unknown.supportapp.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("managers")
public class ManagerController {

    private ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }


    @GetMapping("login")
    public boolean login(@RequestHeader String email, @RequestHeader String password){
        ManagerDto managerDto = new ManagerDto();
        managerDto.setEmail(email);
        managerDto.setPassword(password);

        return managerService.login(managerDto);

    }

    @GetMapping("id/by-email/{email}")
    public Long loadIdByEmail(@PathVariable String email){
        return managerService.loadIdByEmail(email);
    }
}
