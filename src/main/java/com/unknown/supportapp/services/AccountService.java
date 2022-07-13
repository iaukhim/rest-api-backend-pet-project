package com.unknown.supportapp.services;


import com.unknown.supportapp.dto.acccount.AccountDto;

import java.util.List;

public interface AccountService {

    void saveAccount(AccountDto accountDto);

    void update(AccountDto accountDto);

    AccountDto loadByEmail(String email);

    AccountDto loadById(Long id);

    List<AccountDto> loadAll();

    void delete(Long id);

    boolean logIn(AccountDto accountDto);

    String registration(String email);

    boolean checkExistence(String email);

    String confirmationCode(String email);

    void changePassword(AccountDto accountDto);

    Long loadIdByEmail(String email);
}
