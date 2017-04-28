package com.anushka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by rxd2095 on 4/17/17.
 */
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Customer customer;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate orderDate;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = ProductsOrders.class)
    private List<ProductsOrders> productsOrders;
    private double orderSubTotal;
    private double orderTax;
    private double orderTotal;

    public Orders() {
    }

    public Orders(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<ProductsOrders> getProductsOrders() {
        return productsOrders;
    }

    public void setProductsOrders(List<ProductsOrders> productsOrders) {
        this.productsOrders = productsOrders;
        double ordersSubTotal = 0;
        double ordersTax = 0;
        double ordersTotal = 0;
        for (ProductsOrders po : this.getProductsOrders()) {
            ordersSubTotal += po.getProductSubTotal();
            ordersTax += po.getProductSubTotal() * TaxConstants.COBB_COUNTY_GEORGIA_TAX;
            ordersTotal += po.getProductSubTotal() + po.getProductSubTotal() * TaxConstants.COBB_COUNTY_GEORGIA_TAX;
        }
        this.setOrderSubTotal(ordersSubTotal);
        this.setOrderTax(ordersTax);
        this.setOrderTotal(ordersTotal);
    }

    public double getOrderSubTotal() {
        return orderSubTotal;
    }

    public void setOrderSubTotal(double orderSubTotal) {
        this.orderSubTotal = orderSubTotal;
    }

    public double getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(double orderTax) {
        this.orderTax = orderTax;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
}
