package com.anushka.service;

import com.anushka.entity.Orders;
import com.anushka.entity.ProductsOrders;
import com.anushka.entity.TaxConstants;
import com.anushka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rxd2095 on 4/20/17.
 */
@Component
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void setOrderSubTotal(Orders order) {
        Orders updatedOrder = orderRepository.findOne(order.getId());
        double orderSubTotal = 0;
        for (ProductsOrders po : order.getProductsOrders()) {
            orderSubTotal += po.getProductSubTotal();
        }
        updatedOrder.setOrderSubTotal(orderSubTotal);
    }

    public void setOrderTax(Orders order) {
        Orders updatedOrder = orderRepository.findOne(order.getId());
        double orderTax = 0;
        for (ProductsOrders po : order.getProductsOrders()) {
            orderTax += po.getProductSubTotal() * TaxConstants.COBB_COUNTY_GEORGIA_TAX;
        }
        updatedOrder.setOrderTax(orderTax);
    }

    public void setOrderTotal(Orders order) {
        Orders updatedOrder = orderRepository.findOne(order.getId());
        double orderTotal = 0;
        for (ProductsOrders po : order.getProductsOrders()) {
            orderTotal += po.getProductSubTotal();
        }
        updatedOrder.setOrderTotal(orderTotal);
    }

}
