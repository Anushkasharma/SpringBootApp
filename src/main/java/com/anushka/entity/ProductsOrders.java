package com.anushka.entity;

import com.anushka.utility.CurrencyFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rxd2095 on 4/18/17.
 */
@Entity
public class ProductsOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @JsonIgnore
    @OneToOne(orphanRemoval = true)
    private Product product;
    private double productQty;
    private double productSubTotal;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "productsOrders")
    private List<Orders> orders;

    public ProductsOrders() {
    }

    public ProductsOrders(Product product, double productQty) {
        this.product = product;
        this.productQty = productQty;
        this.setProductSubTotal(CurrencyFormat.round((product.getPrice() * productQty), 2));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getProductQty() {
        return productQty;
    }

    public void setProductQty(double productQty) {
        this.productQty = productQty;
    }

    public double getProductSubTotal() {
        return productSubTotal;
    }

    public void setProductSubTotal(double productSubTotal) {
        this.productSubTotal = productSubTotal;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
