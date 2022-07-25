package com.unknown.supportapp.dao;


import com.unknown.supportapp.entities.Account;

import java.util.List;

public interface AccountDao extends AbstractDao<Account>{

    Account loadByEmail (String Email);

    boolean logIn(Account account);

    boolean checkAccountExistence(String email);

    void changePassword(Account account);

    Long loadIdByEmail(String email);

}
