package com.unknown.supportapp.config;

import com.unknown.supportapp.dao.*;
import com.unknown.supportapp.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class DbInit implements ApplicationRunner {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OwnedProductDao ownedProductDao;

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private AbstractDao abstractDao;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {

        accountDao.save(new Account("new_account@mail.com", "100"));
        managerDao.save(new Manager("manager_mail@mai.com", "100"));

        Product laptop = productDao.save(new Product("laptop", "IdeaPad 3 15ITL05"));
        Product smartphone = productDao.save(new Product("smartphone", "Redmi Note 11 4GB/64GB"));
        Product monitor = productDao.save(new Product("monitor", "UltraGear 24GN600-B"));
        Product printer = productDao.save(new Product("printer", "LaserJet Pro M28a"));

        OwnedProduct ownedLaptop = new OwnedProduct(laptop, "serial-number", accountDao.loadById(1L));
        OwnedProduct ownedProduct = ownedProductDao.saveProduct(ownedLaptop);
        OwnedProduct ownedSmartphone = new OwnedProduct();
        ownedSmartphone.setProduct(smartphone);
        ownedSmartphone.setSerialNumber("abc");
        Account account = accountDao.loadById(1L);
        account.addOwnedProduct(ownedSmartphone);
        accountDao.update(account);
        Ticket ticket = new Ticket(ownedProduct, "broken laptop", "something went wrong", true, accountDao.loadById(1L), managerDao.loadByEmail("manager_mail@mai.com"));
        Ticket ticket1 = new Ticket(ownedProduct, "broken laptop", "something went wrong", true, accountDao.loadById(1L), null);
        ticketDao.save(ticket);
        ticketDao.save(ticket1);
    }
}
