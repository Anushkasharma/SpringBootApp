package com.anushka.utility;

import com.anushka.controller.CustomerController;
import com.anushka.controller.OrderController;
import com.anushka.controller.ProductController;
import com.anushka.entity.*;
import com.anushka.repository.CustomerRepository;
import com.anushka.repository.OrderRepository;
import com.anushka.repository.ProductRepository;
import com.anushka.service.CustomerService;
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
 * http://blog.flowersacrossmelbourne.com.au/uncategorized/40-of-the-worlds-weirdest-flowers/
 */
@ActiveProfiles("test")
public abstract class AbstractAnushkaTestDataSetup {

    public MockMvc orderMockMvc;
    public MockMvc productMockMvc;
    public MockMvc customerMockMvc;

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
    public CustomerService customerService;

    @Autowired
    public ProductService productService;

    @Autowired
    public OrderController orderController;

    @Autowired
    public ProductController productController;

    @Autowired
    public CustomerController customerController;

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

        orderMockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        productMockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        customerMockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
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

        Product product1 = productRepository.findByProductName("Monkey Face Orchid");
        Product product2 = productRepository.findByProductName("Corpse Flower");
        Product product3 = productRepository.findByProductName("Purple Sunflower");
        Product product4 = productRepository.findByProductName("Naked Man Orchid");

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

        Customer mohan = customerRepository.findByFirstName("Mohan");
        Customer sanaya = customerRepository.findByFirstName("Sanaya");

        Orders orders1 = new Orders();
        orders1.setOrderDate(LocalDate.of(2017, Month.APRIL, 1));
        orders1.setCustomer(mohan);
        orders1.setProductsOrders(order1);

        Orders orders2 = new Orders();
        orders2.setOrderDate(LocalDate.of(2017, Month.APRIL, 2));
        orders2.setCustomer(sanaya);
        orders2.setProductsOrders(order2);

        Orders orders3 = new Orders();
        orders3.setOrderDate(LocalDate.of(2017, Month.APRIL, 3));
        orders3.setCustomer(mohan);
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

        Customer mohan = new Customer();
        Customer sanaya = new Customer();
        mohan.setFirstName("Mohan");
        mohan.setLastName("Lal");
        mohan.setBirthDay(LocalDate.of(1990, Month.JULY, 1));
        sanaya.setFirstName("Sanaya");
        sanaya.setLastName("Irani");
        sanaya.setBirthDay(LocalDate.of(1995, Month.APRIL, 1));
        customerRepository.save(mohan);
        customerRepository.save(sanaya);

        return customerRepository;
    }

    @Bean
    @Primary
    public ProductRepository setProductRepository() throws SQLException {

        Product product1 = new Product(ProductType.ORCHID, "Monkey Face Orchid", 200.00);
        Product product2 = new Product(ProductType.DAISY, "Corpse Flower", 100.99);
        Product product3 = new Product(ProductType.SUNFLOWER, "Purple Sunflower", 300.00);
        Product product4 = new Product(ProductType.ORCHID, "Naked Man Orchid", 19.99);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        return productRepository;
    }

}
