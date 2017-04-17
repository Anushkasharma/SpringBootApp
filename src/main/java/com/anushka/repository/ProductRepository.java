package com.anushka.repository;

import com.anushka.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by rxd2095 on 4/14/17.
 */
@Component
public interface ProductRepository extends JpaRepository<Product, Long> {
}
