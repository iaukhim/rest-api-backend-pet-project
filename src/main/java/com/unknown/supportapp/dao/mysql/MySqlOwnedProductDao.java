package com.unknown.supportapp.dao.mysql;


import com.unknown.supportapp.dao.OwnedProductDao;
import com.unknown.supportapp.entities.Account;
import com.unknown.supportapp.entities.OwnedProduct;
import com.unknown.supportapp.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;

@Repository
public class MySqlOwnedProductDao extends MySqlAbstractDao<OwnedProduct> implements OwnedProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public MySqlOwnedProductDao() {
    }

    public MySqlOwnedProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<OwnedProduct> loadUsersProducts(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OwnedProduct> criteriaQuery = cb.createQuery(OwnedProduct.class);
        Root<OwnedProduct> ownedProductRoot = criteriaQuery.from(OwnedProduct.class);

        Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        Root<Account> subqueryRoot = subquery.from(Account.class);
        subquery.select(subqueryRoot.get("id"));
        subquery.where(cb.equal(subqueryRoot.get("email"), cb.parameter(String.class, "email")));

        criteriaQuery.select(ownedProductRoot).where(cb.equal(ownedProductRoot.get("owner").get("id"), subquery));
        TypedQuery<OwnedProduct> query = entityManager.createQuery(criteriaQuery).setParameter("email", email);

        List<OwnedProduct> resultList = query.getResultList();
        return resultList;
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
        if (checkSerial(newValue)) {
            product.setSerialNumber(newValue);
            entityManager.merge(product);
            return true;
        }
        return false;
    }

    @Override
    Class<OwnedProduct> getClazz() {
        return OwnedProduct.class;
    }

    @Override
    public void deleteById(Long id) {
        OwnedProduct entityForDelete = entityManager.find(OwnedProduct.class, id);
        if (entityForDelete == null) {
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
    public String loadModelById(Long id) {
        OwnedProduct ownedProduct = findById(id);
        return ownedProduct.getProduct().getModel();
    }
}
