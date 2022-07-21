package com.unknown.supportapp.services.security;

import com.unknown.supportapp.dao.AccountDao;
import com.unknown.supportapp.dao.ManagerDao;
import com.unknown.supportapp.entities.Account;
import com.unknown.supportapp.entities.Manager;
import com.unknown.supportapp.exceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    private AccountDao accountDao;

    private ManagerDao managerDao;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(AccountDao accountDao, ManagerDao managerDao, PasswordEncoder passwordEncoder) {
        this.accountDao = accountDao;
        this.managerDao = managerDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ArrayList<GrantedAuthority> managerAuthority = new ArrayList<>();


        ArrayList<GrantedAuthority> userAuthority = new ArrayList<>();


        Manager manager;
        Account account;
        try {
             manager = managerDao.loadByEmail(username);
             managerAuthority.add(new SimpleGrantedAuthority(manager.getRole()));
             return new User(manager.getEmail(), passwordEncoder.encode(manager.getPassword()), managerAuthority);
        }
        catch (NoSuchEntityException e) {
            }
        try {
            account = accountDao.loadByEmail(username);
            userAuthority.add(new SimpleGrantedAuthority(account.getRole()));
            return new User(account.getEmail(), passwordEncoder.encode(account.getPassword()), userAuthority);
        }
        catch (NoSuchEntityException e){
        }

        throw new UsernameNotFoundException(String.format("User '%s' not found", username));
    }

}
