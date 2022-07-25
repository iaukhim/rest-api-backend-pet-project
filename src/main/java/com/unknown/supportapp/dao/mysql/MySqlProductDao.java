package com.unknown.supportapp.dao.mysql;

import com.unknown.supportapp.dao.ProductDao;
import com.unknown.supportapp.entities.Product;
import com.unknown.supportapp.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MySqlProductDao extends MySqlAbstractDao<Product> implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public MySqlProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long loadIdByModel(String model) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product as p WHERE p.model = :model", Product.class);
        query.setParameter("model", model);

        try {
            Product product = query.getSingleResult();
            return product.getId();
        } catch (NoResultException e) {
            throw new NoSuchEntityException("No product with that model", e);
        }
    }

    @Override
    public List<Product> loadProductsByType(String type) {
        entityManager.joinTransaction();
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product as p WHERE p.type = :type", Product.class);
        query.setParameter("type", type);
        List<Product> productList = query.getResultList();

        if(productList.isEmpty()){
            throw new NoSuchEntityException("No products with such type");
        }
        return productList;
    }

    @Override
    public List<String> loadAllProductTypes() {
        TypedQuery<String> query = entityManager.createQuery("SELECT distinct p.type FROM Product as p", String.class);
        return query.getResultList();
    }

    @Override
    Class<Product> getClazz() {
        return Product.class;
    }

}
