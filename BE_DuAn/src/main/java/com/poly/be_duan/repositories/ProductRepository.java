package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Product;
import com.poly.be_duan.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById_products(Integer id);
}
