package com.anushka.repository;

import com.anushka.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by rxd2095 on 4/30/17.
 */
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Customer> getAllCustomersUnordered() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Customer> from = criteriaQuery.from(Customer.class);

        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = em.createQuery(select);
        List<Object> resultlist = typedQuery.getResultList();

        em.close();

        return (List<Customer>)(Object) resultlist;
    }

    @Override
    public List<Customer> getAllCustomersOrderedByLastName() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Customer> from = criteriaQuery.from(Customer.class);

        CriteriaQuery<Object> select = criteriaQuery.select(from);
        select.orderBy(criteriaBuilder.asc(from.get("lastName")));
        TypedQuery<Object> typedQuery1 = em.createQuery(select);
        List<Object> resultlist = typedQuery1.getResultList();

        return (List<Customer>)(Object) resultlist;
    }
}
