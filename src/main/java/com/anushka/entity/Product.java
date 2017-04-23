package com.anushka.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rxd2095 on 4/14/17.
 */
@Entity(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ProductType productType;
    private String productName;
    private double price;
    @OneToOne
    private ProductsOrders orders;

    public Product() {
    }

    public Product(ProductType productType, String productName, double price) {
        this.productType = productType;
        this.productName = productName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductsOrders getOrders() {
        return orders;
    }

    public void setOrders(ProductsOrders orders) {
        this.orders = orders;
    }
}
