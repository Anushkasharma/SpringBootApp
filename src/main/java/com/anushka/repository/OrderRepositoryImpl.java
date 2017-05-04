package com.anushka.repository;

import com.anushka.data.OrdersRowMapper;
import com.anushka.entity.Customer;
import com.anushka.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxd2095 on 5/3/17.
 */
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Orders> getAllOrdersByCustomer_usingCriteriaAndPredicatesWithJoin(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
        Root<Orders> orders = cq.from(Orders.class);

        cq.select(orders);
        cq.distinct(true);
        Join<Orders, Customer> cust =
                orders.join("customer", JoinType.INNER);

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

        TypedQuery<Orders> q = em.createQuery(cq);
        if (id != null) {
            q.setParameter("id", id);
        }
        return q.getResultList();
    }

    @Override
    public double getCumulativeOrderAmountByCustomer_usingCriteriaAndPredicatesWithJoin(Long id) {
        double cumulativeTotal = 0.0;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
        Root<Orders> orders = cq.from(Orders.class);

        cq.select(orders);
        cq.distinct(true);
        Join<Orders, Customer> cust =
                orders.join("customer", JoinType.INNER);

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

        TypedQuery<Orders> q = em.createQuery(cq);
        if (id != null) {
            q.setParameter("id", id);
        }

        for (Orders o : q.getResultList()) {
            cumulativeTotal += o.getOrderTotal();
        }
        return cumulativeTotal;
    }

    @Override
    public List<Orders> getAllOrdersByCustomer_usingJDBCTemplate(Long id) {
        String query = "" +
                "SELECT " +
                "O.ID, " +
                "O.ORDER_DATE, " +
                "O.ORDER_SUB_TOTAL, " +
                "O.ORDER_TAX, " +
                "O.ORDER_TOTAL, " +
                "O.CUSTOMER_ID, " +
                "C.BIRTH_DAY, " +
                "C.FIRST_NAME, " +
                "C.LAST_NAME " +
                "FROM ORDERS O INNER JOIN CUSTOMER C " +
                "ON O.CUSTOMER_ID = C.ID " +
                "WHERE C.ID = ?";

        List<Orders> orders = jdbcTemplate.query(
                query, new OrdersRowMapper(), id);

        return orders;
    }
}
