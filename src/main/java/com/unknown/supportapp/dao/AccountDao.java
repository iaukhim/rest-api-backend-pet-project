package com.unknown.supportapp.dao;


import com.unknown.supportapp.entities.Account;

import java.util.List;

public interface AccountDao {

    List<Account> loadAll();

    Account loadByEmail (String Email);

    Account loadById (Long id);

    void update (Account account);

    void save (Account account);

    void delete(Long id);

    boolean logIn(Account account);

    boolean checkAccountExistence(String email);

    void changePassword(Account account);

    Long loadIdByEmail(String email);

}
