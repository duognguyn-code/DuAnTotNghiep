package com.poly.be_duan.service;

import com.poly.be_duan.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAll();
  
    public List<Product> getByColor(String color, String design, String material, String size);


    public Product create(Product product_detail);

    Page<Product> getAll (Pageable page);

    List<Product> save(List<Product> entities);
//    public Product getProductByID(Integer id);

    Optional<Product> findById(Integer id);
    public Product update(Product product, Integer id);

    public void delete(Integer id);
}
