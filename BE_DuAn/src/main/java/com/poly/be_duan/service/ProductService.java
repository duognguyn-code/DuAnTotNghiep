package com.poly.be_duan.service;

import com.poly.be_duan.entities.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAll();

    public Product create(Product product);

    public Product getProductByID(Integer id);

    public Product update(Product product);

    public void delete(Integer id);
}
