package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    public Product findProductById_products(Integer id_products);

    @Query("SELECT p FROM Product p WHERE (p.color.name LIKE %?1% " +
            "or p.design.name LIKE %?2% o" +
            "r p.material.name LIKE %?3% " +
            "or p.size.name LIKE %?4% )")
    public List<Product> getByColor(String color, String design, String material, String size);
}
