package com.anushka.configuration;

import com.anushka.entity.*;
import com.anushka.repository.CustomerRepository;
import com.anushka.repository.OrderRepository;
import com.anushka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxd2095 on 4/20/17.
 */
@ActiveProfiles({"default", "test"})
public abstract class AbstractAnushkaDataSetup {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public OrderService orderService;

    @Bean
    @Primary
    public OrderRepository setOrderRepository() {
        Product product1 = new Product(ProductType.DAISY, "Yellow Daisy", 5.00);
        Product product2 = new Product(ProductType.DAISY, "White Daisy", 5.50);
        Product product3 = new Product(ProductType.ROSE, "Red Roses", 24.00);
        Product product4 = new Product(ProductType.LILLY, "Easter Lilly", 10.00);

        List<ProductsOrders> order1 = new ArrayList<>();
        List<ProductsOrders> order2 = new ArrayList<>();
        List<ProductsOrders> order3 = new ArrayList<>();
        // order 1 going to anushka
        ProductsOrders productsOrders1 = new ProductsOrders(product1, 1, calculateProductSubTotal(product1, 1));
        ProductsOrders productsOrders2 = new ProductsOrders(product2, 2, calculateProductSubTotal(product2, 2));
        // order 2 going to chad
        ProductsOrders productsOrders3 = new ProductsOrders(product3, 1, calculateProductSubTotal(product3, 1));
        // order 3 going to anushka
        ProductsOrders productsOrders4 = new ProductsOrders(product4, 10, calculateProductSubTotal(product4, 10));

        order1.add(productsOrders1);
        order1.add(productsOrders2);
        order2.add(productsOrders3);
        order3.add(productsOrders4);

        if (customerRepository.count() == 0) {
            customerRepository = setCustomerRepository();
        }

        Customer anushka = customerRepository.findByFirstName("Anushka");
        Customer chad = customerRepository.findByFirstName("Chad");

        Orders orders1 = new Orders();
        orders1.setOrderDate(LocalDate.of(2017, Month.APRIL, 1));
        orders1.setCustomer(anushka);
        orders1.setProductsOrders(order1);
        orders1.setOrderSubTotal(calculateOrderSubTotal(orders1));
        orders1.setOrderTax(orders1.getOrderSubTotal() * TaxConstants.COBB_COUNTY_GEORGIA_TAX);
        orders1.setOrderTotal(orders1.getOrderSubTotal() + orders1.getOrderTax());

        Orders orders2 = new Orders();
        orders2.setOrderDate(LocalDate.of(2017, Month.APRIL, 2));
        orders2.setCustomer(chad);
        orders2.setProductsOrders(order2);
        orders2.setOrderSubTotal(calculateOrderSubTotal(orders2));
        orders2.setOrderTax(orders2.getOrderSubTotal() * TaxConstants.COBB_COUNTY_GEORGIA_TAX);
        orders2.setOrderTotal(orders2.getOrderSubTotal() + orders2.getOrderTax());

        Orders orders3 = new Orders();
        orders3.setOrderDate(LocalDate.of(2017, Month.APRIL, 3));
        orders3.setCustomer(anushka);
        orders3.setProductsOrders(order3);
        orders3.setOrderSubTotal(calculateOrderSubTotal(orders3));
        orders3.setOrderTax(orders3.getOrderSubTotal() * TaxConstants.COBB_COUNTY_GEORGIA_TAX);
        orders3.setOrderTotal(orders3.getOrderSubTotal() + orders3.getOrderTax());

        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(orders1);
        ordersList.add(orders2);
        ordersList.add(orders3);

        ordersList.forEach(orderRepository::save);
        return orderRepository;
    }

    private double calculateOrderSubTotal(Orders orders) {
        double subTotal = 0;
        for (ProductsOrders po : orders.getProductsOrders()) {
            subTotal += po.getProductSubTotal();
        }
        return subTotal;
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

    public double calculateProductSubTotal(Product product1, int productQuantity) {
        return product1.getPrice() * productQuantity;
    }

}
