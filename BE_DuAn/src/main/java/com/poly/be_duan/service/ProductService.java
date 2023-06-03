package com.poly.be_duan.service;

import com.poly.be_duan.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public List<Product> getAll();

    public Product create(Product product);

    Page<Product> getAll (Pageable page);

//    public Product getProductByID(Integer id);

    public Product update(Product product);

    public void delete(Integer id);
}
