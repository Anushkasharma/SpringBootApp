package com.anushka.configuration;

import com.anushka.entity.*;
import com.anushka.repository.CustomerRepository;
import com.anushka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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

    @Autowired
    CustomerRepository customerRepository;

    @Bean
    @Primary
    public OrderRepository setOrderRepository() {
        // --------------------------------------------------------------------
        // PRODUCT
        // --------------------------------------------------------------------
        Product product1 = new Product(ProductType.DAISY, "Yellow Daisy", 5.00);
        Product product2 = new Product(ProductType.DAISY, "White Daisy", 5.50);
        Product product3 = new Product(ProductType.ROSE, "Red Roses", 24.00);
        Product product4 = new Product(ProductType.LILLY, "Easter Lilly", 10.00);

        // --------------------------------------------------------------------
        // ORDERS
        // --------------------------------------------------------------------
        Orders orders1 = new Orders();
        Orders orders2 = new Orders();
        Orders orders3 = new Orders();

        orders1.setOrderDate(LocalDate.of(2017, Month.APRIL, 1));
        orders2.setOrderDate(LocalDate.of(2017, Month.APRIL, 2));
        orders3.setOrderDate(LocalDate.of(2017, Month.APRIL, 3));

        // --------------------------------------------------------------------
        // CUSTOMER
        // --------------------------------------------------------------------


        // --------------------------------------------------------------------
        // PRODUCT_ORDERS
        // --------------------------------------------------------------------
        List<ProductsOrders> order1 = new ArrayList<>();
        List<ProductsOrders> order2 = new ArrayList<>();
        List<ProductsOrders> order3 = new ArrayList<>();
        // order 1 to anushka
        ProductsOrders productsOrders1 = new ProductsOrders(product1, 1, calculateProductSubTotal(product1, 1));
        ProductsOrders productsOrders2 = new ProductsOrders(product2, 2, calculateProductSubTotal(product2, 2));
        // order 2 to chad
        ProductsOrders productsOrders3 = new ProductsOrders(product3, 1, calculateProductSubTotal(product3, 1));
        // order 3 to anushka
        ProductsOrders productsOrders4 = new ProductsOrders(product4, 10, calculateProductSubTotal(product4, 10));

        order1.add(productsOrders1);
        order1.add(productsOrders2);
        order2.add(productsOrders3);
        order3.add(productsOrders4);

        if (customerRepository.count() == 0) {
            customerRepository = setCustomerRepository();
        }

//        Customer anushka = customerRepository.findByFirstName("Anushka");
//        Customer chad = customerRepository.findByFirstName("Chad");
        Customer anushka = new Customer(1L, "Anushka", "Sharma", null);
        Customer chad = new Customer(2L, "Chad", "Davis", null);
        orders1.setCustomer(anushka);
        orders2.setCustomer(chad);
        orders3.setCustomer(anushka);

        orders1.setProductsOrders(order1);
        orders2.setProductsOrders(order2);
        orders3.setProductsOrders(order3);

        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(orders1);
        ordersList.add(orders2);
        ordersList.add(orders3);

        ordersList.forEach(orderRepository::save);
        return orderRepository;
    }

    @Bean
    @Primary
    public CustomerRepository setCustomerRepository() {
        Customer anushka = new Customer();
        Customer chad = new Customer();
        anushka.setFirstName("Anushka");
        anushka.setLastName("Sharma");
        chad.setFirstName("Chad");
        chad.setLastName("Davis");
        customerRepository.save(anushka);
        customerRepository.save(chad);
        return customerRepository;
    }

    private double calculateProductSubTotal(Product product1, int productQuantity) {
        return product1.getPrice() * productQuantity;
    }



}
