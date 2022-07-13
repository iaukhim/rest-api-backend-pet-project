package com.unknown.supportapp.dao.mysql;


import com.unknown.supportapp.dao.OwnedProductDao;
import com.unknown.supportapp.entities.OwnedProduct;
import com.unknown.supportapp.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MySqlOwnedProductDao implements OwnedProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public MySqlOwnedProductDao() {
    }

    public MySqlOwnedProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<OwnedProduct> loadUsersProducts(String email) {
        TypedQuery<OwnedProduct> query = entityManager.createQuery("SELECT o FROM OwnedProduct as o WHERE o.ownerId IN (SELECT a.id FROM Account as a WHERE a.email = :email)", OwnedProduct.class);
        query.setParameter("email", email);
        List<OwnedProduct> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void saveProduct(OwnedProduct product) {
        entityManager.merge(product);
    }
    @Override
    public boolean changeSerial(String oldValue, String newValue) {
        TypedQuery<OwnedProduct> loadProductQuery = entityManager.createQuery("SELECT o FROM OwnedProduct as o WHERE o.serialNumber = :serialNumber", OwnedProduct.class);
        loadProductQuery.setParameter("serialNumber", oldValue);
        OwnedProduct product = null;
        try {
            product = loadProductQuery.getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchEntityException("No product with such serial number");
        }
        if(checkSerial(newValue)){
            product.setSerialNumber(newValue);
            entityManager.merge(product);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(Long id) {
        OwnedProduct entityForDelete = entityManager.find(OwnedProduct.class, id);
        if (entityForDelete == null){
            throw new NoSuchEntityException("There is no owned product with such id");
        }
        entityManager.remove(entityForDelete);
    }

    @Override
    public boolean checkSerial(String serialNumber) {
        TypedQuery<OwnedProduct> query = entityManager.createQuery("SELECT o FROM OwnedProduct as o WHERE o.serialNumber = :serialNumber", OwnedProduct.class);
        query.setParameter("serialNumber", serialNumber);
        if (query.getResultList().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public OwnedProduct loadById(Long id) {
        OwnedProduct ownedProduct = entityManager.find(OwnedProduct.class, id);
        if (ownedProduct == null){
            throw new NoSuchEntityException("There is no owned product with such id");
        }
        return ownedProduct;
    }

    @Override
    public String loadModelById(Long id) {
        OwnedProduct ownedProduct = loadById(id);
        return ownedProduct.getModel();
    }
}
