package com.anushka.configuration;

import com.anushka.entity.Product;
import com.anushka.entity.Orders;
import com.anushka.entity.ProductType;
import com.anushka.repository.OrderRepository;
import com.anushka.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rxd2095 on 4/14/17.
 */
@Configuration
@ComponentScan
@Profile("default")
public class AnushkaConfiguration {

    @Autowired
    OrderRepository orderRepository;

    @Bean
    @Primary
    public OrderRepository setOrderRepository() {
        Product product1 = new Product(ProductType.DAISY, "Yellow Daisy", 5.00);
        Product product2 = new Product(ProductType.DAISY, "White Daisy", 5.50);
        Product product3 = new Product(ProductType.ROSE, "Red Roses", 24.00);
        Product product4 = new Product(ProductType.LILLY, "Easter Lilly", 10.00);
        List<Product> productList1 = Arrays.asList(product1, product4);
        List<Product> productList2 = Arrays.asList(product1, product3);
        List<Product> productList3 = Arrays.asList(product2);
        List<Orders> ordersList = Arrays.asList(
                new Orders(product1, LocalDate.of(2017, Month.APRIL, 1), 1, 24.00),
                new Orders(product2, LocalDate.of(2017, Month.APRIL, 2), 2, 20.00)
        );
        ordersList.forEach(orderRepository::save);
        return orderRepository;
    }

}
