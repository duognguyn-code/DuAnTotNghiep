package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
//    public Product findProductById_products(Integer id_products);

    @Query("SELECT p FROM Product p WHERE (p.color.name LIKE %?1% " +
            "or p.design.name LIKE %?2% o" +
            "r p.material.name LIKE %?3% " +
            "or p.size.name LIKE %?4% )")
    public List<Product> getByColor(String color, String design, String material, String size);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% " +
            "and p.color.name LIKE %?2% " +
            "and p.material.name LIKE %?3% " +
            "and p.size.name LIKE %?4% " +
            "and p.design.name LIKE %?5%"+
            "and p.price between ?6 and ?7 "+
            "and p.status = ?8"
           )
    public List<Product> search(String name, String color, String material, String size, String design, BigDecimal min, BigDecimal max, Integer status);

    @Query("select MIN(p.price) FROM Product p")
    public BigDecimal searchMin();
    @Query("select MAX(p.price) FROM Product p")
    public BigDecimal searchMax();
}
