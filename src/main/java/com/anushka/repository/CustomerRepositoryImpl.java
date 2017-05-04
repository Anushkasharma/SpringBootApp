package com.anushka.repository;

import com.anushka.data.CustomerRowMapper;
import com.anushka.entity.Customer;
import com.anushka.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxd2095 on 4/30/17.
 */
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getAllCustomersUnordered_usingCriteria() {
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
    public List<Customer> getAllCustomersOrderedByLastName_usingCriteria() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Customer> from = criteriaQuery.from(Customer.class);

        CriteriaQuery<Object> select = criteriaQuery.select(from);
        select.orderBy(criteriaBuilder.asc(from.get("lastName")));
        TypedQuery<Object> typedQuery1 = em.createQuery(select);
        List<Object> resultList = typedQuery1.getResultList();

        return (List<Customer>)(Object) resultList;
    }

    @Override
    public List<Customer> getCustomerById_usingCriteriaWithPredicate(final Long id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> cust = cq.from(Customer.class);

        cq.select(cust);
        cq.distinct(true);
        Join<Customer, Orders> orders =
                cust.join("orders", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<Predicate>();
        if (id != null) {
            ParameterExpression<Long> p = cb.parameter(Long.class, "id");
            criteria.add(cb.equal(cust.get("id"), p));
        }
        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        } else if (criteria.size() == 1) {
            cq.where(criteria.get(0));
        } else {
            cq.where(cb.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Customer> q = em.createQuery(cq);
        if (id != null) {
            q.setParameter("id", id);
        }
        return q.getResultList();

    }

    public Customer getCustomerById_usingJDBCTemplate(Long custId) {

        String sql = "" +
                "SELECT " +
                "   ID, " +
                "   BIRTH_DAY, " +
                "   FIRST_NAME, " +
                "   LAST_NAME " +
                "FROM CUSTOMER " +
                "WHERE ID = ?";
        Customer customer = (Customer) jdbcTemplate.queryForObject(
                sql,
                new Object[] { custId },
                new CustomerRowMapper()
        );
        return customer;

    }
}
