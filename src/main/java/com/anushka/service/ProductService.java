package com.anushka.service;

import com.anushka.entity.Product;
import com.anushka.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by rxd2095 on 4/15/17.
 */
@Component
public class ProductService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findAllProductsOrderedByPriceAsc() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Product> from = criteriaQuery.from(Product.class);

        CriteriaQuery<Object> select = criteriaQuery.select(from);
        select.orderBy(criteriaBuilder.asc(from.get("price")));
        TypedQuery<Object> typedQuery = em.createQuery(select);
        List<Object> resultlist = typedQuery.getResultList();

        return (List<Product>)(Object) resultlist;
    }

}
