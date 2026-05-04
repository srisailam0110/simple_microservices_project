package com.simple.microservices.product.repository;
 
import com.simple.microservices.product.entity.Product;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findBySku(String sku);
}
 