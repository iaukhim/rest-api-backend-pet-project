package com.unknown.supportapp.services.impl;

import com.unknown.supportapp.dao.AccountDao;
import com.unknown.supportapp.dto.acccount.AccountDto;
import com.unknown.supportapp.entities.Account;
import com.unknown.supportapp.entities.converters.Converter;
import com.unknown.supportapp.services.AccountService;
import com.unknown.supportapp.services.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {


    private AccountDao accountDao;

    private Converter converter;
    private MailService mailService;

    public AccountServiceImpl() {
    }

    @Autowired
    public AccountServiceImpl(AccountDao accountDao, Converter converter, MailService mailService) {
        this.accountDao = accountDao;
        this.converter = converter;
        this.mailService = mailService;
    }

    @Override
    public void update(AccountDto accountDto) {
        Account account = converter.convertAccountToEntity(accountDto);
        accountDao.update(account);
    }

    @Override
    public void delete(Long id) {
        accountDao.deleteById(id);
    }

    @Override
    public void saveAccount(AccountDto accountDto) {
        Account account = converter.convertAccountToEntity(accountDto);
        accountDao.save(account);
    }

    @Override
    public AccountDto loadByEmail(String email) {
        Account account = accountDao.loadByEmail(email);

        AccountDto accountDto = converter.convertAccountToDto(account);
        return accountDto;
    }

    @Override
    public AccountDto loadById(Long id) {
        Account account = accountDao.findById(id);
        return converter.convertAccountToDto(account);
    }

    @Override
    public List<AccountDto> loadAll() {
        List<Account> entities = accountDao.loadAll();
        return converter.convertAccountToDto(entities);
    }

    @Override
    public boolean checkExistence(String email) {
        boolean result = accountDao.checkAccountExistence(email);
        return result;
    }

    @Override
    public String confirmationCode(String email) {
        String code = mailService.sendConfirmationEmail(email);
        return code;
    }

    @Override
    public void changePassword(AccountDto accountDto) {
        Account account = converter.convertAccountToEntity(accountDto);
        accountDao.changePassword(account);

    }

    @Override
    public Long loadIdByEmail(String email) {
        Long id = accountDao.loadIdByEmail(email);
        return id;
    }
}
