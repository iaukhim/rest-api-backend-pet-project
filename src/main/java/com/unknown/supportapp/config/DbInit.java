package com.unknown.supportapp.config;

import com.unknown.supportapp.dao.*;
import com.unknown.supportapp.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class DbInit implements ApplicationRunner {

    private AccountDao accountDao;

    private ProductDao productDao;

    private OwnedProductDao ownedProductDao;

    private ManagerDao managerDao;

    private TicketDao ticketDao;

    @Autowired
    public DbInit(AccountDao accountDao, ProductDao productDao, OwnedProductDao ownedProductDao, ManagerDao managerDao, TicketDao ticketDao) {
        this.accountDao = accountDao;
        this.productDao = productDao;
        this.ownedProductDao = ownedProductDao;
        this.managerDao = managerDao;
        this.ticketDao = ticketDao;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {

        accountDao.save(new Account("new_account@mail.com", "100"));
        managerDao.save(new Manager("manager_mail@mai.com", "100"));

        Product laptop = productDao.save(new Product("laptop", "IdeaPad 3 15ITL05"));
        Product smartphone = productDao.save(new Product("smartphone", "Redmi Note 11 4GB/64GB"));
        Product monitor = productDao.save(new Product("monitor", "UltraGear 24GN600-B"));
        Product printer = productDao.save(new Product("printer", "LaserJet Pro M28a"));

        OwnedProduct ownedLaptop = new OwnedProduct(laptop, "serial-number", accountDao.findById(1L));
        OwnedProduct ownedProduct = ownedProductDao.save(ownedLaptop);
        OwnedProduct ownedSmartphone = new OwnedProduct();
        ownedSmartphone.setProduct(smartphone);
        ownedSmartphone.setSerialNumber("abc");
        Account account = accountDao.findById(1L);
        account.addOwnedProduct(ownedSmartphone);
        accountDao.update(account);
        Ticket ticket = new Ticket(ownedProduct, "broken laptop", "something went wrong", true, accountDao.findById(1L), managerDao.loadByEmail("manager_mail@mai.com"));
        Ticket ticket1 = new Ticket(ownedProduct, "broken laptop", "something went wrong", true, accountDao.findById(1L), null);
        ticketDao.save(ticket);
        ticketDao.save(ticket1);
    }
}
