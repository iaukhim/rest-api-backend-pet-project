package com.unknown.supportapp.config;

import com.unknown.supportapp.dao.AccountDao;
import com.unknown.supportapp.dao.ManagerDao;
import com.unknown.supportapp.entities.Account;
import com.unknown.supportapp.entities.Manager;
import com.unknown.supportapp.exceptions.NoSuchEntityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@EnableWebSecurity
public class SecurityConfig implements UserDetailsService {
    private AccountDao accountDao;

    private ManagerDao managerDao;

    @Autowired
    public SecurityConfig(AccountDao accountDao, ManagerDao managerDao) {
        this.accountDao = accountDao;
        this.managerDao = managerDao;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
            return new User(manager.getEmail(), passwordEncoder().encode(manager.getPassword()), managerAuthority);
        }
        catch (NoSuchEntityException e) {
        }
        try {
            account = accountDao.loadByEmail(username);
            userAuthority.add(new SimpleGrantedAuthority(account.getRole()));
            return new User(account.getEmail(), passwordEncoder().encode(account.getPassword()), userAuthority);
        }
        catch (NoSuchEntityException e){
        }

        throw new UsernameNotFoundException(String.format("User '%s' not found", username));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/accounts/**").hasRole("USER")
                .antMatchers("/managers/**").hasRole("MANAGER")
                .antMatchers("/tickets/**").hasAnyRole("USER", "MANAGER")
                .antMatchers("/owned-products/**").hasRole("USER")
                .antMatchers("/products/**").hasRole("MANAGER")
                .and()
                .formLogin()
                .and()
                .build();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(this);
        return authenticationProvider;
    }

}
