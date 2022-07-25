package com.unknown.supportapp.controllers.rest;

import com.unknown.supportapp.dto.acccount.AccountDto;
import com.unknown.supportapp.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.services.AccountService;
import com.unknown.supportapp.services.OwnedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("accounts")
public class AccountController {

    private AccountService accountService;

    private OwnedProductService ownedProductService;

    @Autowired
    public AccountController(AccountService accountService, OwnedProductService ownedProductService) {
        this.accountService = accountService;
        this.ownedProductService = ownedProductService;
    }


    @GetMapping("by-email/{email}")
    public AccountDto getAccountByEmail(@PathVariable String email) {
       return accountService.loadByEmail(email);
    }


    @GetMapping("/confirmation-code")
    public String sendConfirmationCode(@RequestParam String email){
        return accountService.confirmationCode(email);
    }

    @GetMapping("")
    public List<AccountDto> loadAll(){
        List<AccountDto> accountDtos = accountService.loadAll();
        return accountDtos;
    }

    @PostMapping("")
    public void saveAccount(@RequestBody AccountDto accountDto){
        accountService.saveAccount(accountDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        accountService.delete(id);
    }

    @GetMapping("/{id}")
    public AccountDto getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.loadById(id);
        return accountDto;
    }

    @PutMapping("/change-password")
    public String changePassword(@RequestHeader Long accountId, @RequestHeader String password){
        AccountDto accountDto = new AccountDto();
        accountDto.setId(accountId);
        accountDto.setPassword(password);
        accountService.changePassword(accountDto);
        return "success";
    }

    @GetMapping("/{email}/owned-products")
    public List<OwnedProductDto> loadUsersProducts(@PathVariable String email){
        return ownedProductService.loadUsersProducts("new_account@mail.com");
    }

    @GetMapping("/id/by-email/{email}")
    public Long loadIdByEmail(@PathVariable String email){
        return accountService.loadIdByEmail(email);
    }

    @PutMapping("/update")
    public void update(@RequestBody AccountDto accountDto){
        accountService.update(accountDto);
    }


}
