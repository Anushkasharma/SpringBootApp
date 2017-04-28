package com.anushka.utility;

import com.anushka.controller.OrderController;
import com.anushka.controller.ProductController;
import com.anushka.entity.*;
import com.anushka.repository.CustomerRepository;
import com.anushka.repository.OrderRepository;
import com.anushka.repository.ProductRepository;
import com.anushka.service.OrderService;
import com.anushka.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxd2095 on 4/20/17.
 */
@ActiveProfiles({"test", "default"})
public abstract class AbstractAnushkaTestDataSetup {

    public MockMvc mockMvc;
    public MockMvc productMockMvc;

    @Autowired
    private Environment environment;

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public OrderService orderService;

    @Autowired
    public ProductService productService;

    @Autowired
    public OrderController orderController;

    @Autowired
    public ProductController productController;

    @Autowired
    ApplicationContext applicationContext;

    @Before
    public void setUp() throws SQLException {
        if (productRepository.count() == 0) {
            setProductRepository();
        }
        if (orderRepository.count() == 0) {
            setOrderRepository();
        }
        if (customerRepository.count() == 0) {
            setCustomerRepository();
        }


        String[] profiles = this.environment.getActiveProfiles();
        boolean isTest = false;
        for (String profile : profiles) {
            if ("test".equals(profile)) {
                isTest = true;
            }
        }
        if (isTest) {
            String prop1 = "test.reset.sql.template";
            DBTestUtil.resetAutoIncrementColumns(prop1, applicationContext, "ORDERS", "PRODUCTS_ORDERS", "PRODUCT", "CUSTOMER");
        }

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        productMockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @After
    public void tearDown() {
        // We must delete the Parent entities first, which in this case is those created in the setOrderRepository() method
        // Otherwise, we will get a `org.springframework.dao.DataIntegrityViolationException: could not execute statement;` Exception
        orderRepository.deleteAll();
        // This is a Child entity
        productRepository.deleteAll();
        // This is a Child entity
        customerRepository.deleteAll();
    }

    @Bean
    @Primary
    public OrderRepository setOrderRepository() throws SQLException {

        List<ProductsOrders> order1 = new ArrayList<>();
        List<ProductsOrders> order2 = new ArrayList<>();
        List<ProductsOrders> order3 = new ArrayList<>();

        if (productRepository.count() == 0) {
            productRepository = setProductRepository();
        }

        Product product1 = productRepository.findByProductName("Yellow Daisy");
        Product product2 = productRepository.findByProductName("White Daisy");
        Product product3 = productRepository.findByProductName("Red Roses");
        Product product4 = productRepository.findByProductName("Easter Lilly");

        // order 1 going to anushka
        ProductsOrders productsOrders1 = new ProductsOrders(product1, 1);
        ProductsOrders productsOrders2 = new ProductsOrders(product2, 2);
        // order 2 going to chad
        ProductsOrders productsOrders3 = new ProductsOrders(product3, 1);
        // order 3 going to anushka
        ProductsOrders productsOrders4 = new ProductsOrders(product4, 10);

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

        Orders orders2 = new Orders();
        orders2.setOrderDate(LocalDate.of(2017, Month.APRIL, 2));
        orders2.setCustomer(chad);
        orders2.setProductsOrders(order2);

        Orders orders3 = new Orders();
        orders3.setOrderDate(LocalDate.of(2017, Month.APRIL, 3));
        orders3.setCustomer(anushka);
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
    public CustomerRepository setCustomerRepository() throws SQLException {

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

    @Bean
    @Primary
    public ProductRepository setProductRepository() throws SQLException {

        Product product1 = new Product(ProductType.DAISY, "Yellow Daisy", 5.00);
        Product product2 = new Product(ProductType.DAISY, "White Daisy", 5.50);
        Product product3 = new Product(ProductType.ROSE, "Red Roses", 24.00);
        Product product4 = new Product(ProductType.LILLY, "Easter Lilly", 10.00);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        return productRepository;
    }

}
