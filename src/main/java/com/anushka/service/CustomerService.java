package com.anushka.service;

import com.anushka.entity.Customer;
import com.anushka.repository.CustomerRepository;
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
 * Created by rxd2095 on 4/29/17.
 */
@Component
public class CustomerService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomersUnordered() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Customer> from = criteriaQuery.from(Customer.class);

        //select all records
        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = em.createQuery(select);
        List<Object> resultlist = typedQuery.getResultList();

//        for(Object c : resultlist) {
//            Customer e = (Customer) c;
//            System.out.println("ID : " + e.getId() + " LastName : " + e.getLastName());
//        }

        em.close();

        return (List<Customer>)(Object) resultlist;

    }

    public List<Customer> getAllCustomersOrderedByLastName() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Customer> from = criteriaQuery.from(Customer.class);

        //Ordering the records
        CriteriaQuery<Object> select = criteriaQuery.select(from);
        select.orderBy(criteriaBuilder.asc(from.get("lastName")));
        TypedQuery<Object> typedQuery1 = em.createQuery(select);
        List<Object> resultlist = typedQuery1.getResultList();

//        for(Object c : resultlist){
//            Customer e = (Customer) c;
//            System.out.println("ID : " + e.getId() + " LastName : " + e.getLastName());
//        }

        return (List<Customer>)(Object) resultlist;
    }



}
