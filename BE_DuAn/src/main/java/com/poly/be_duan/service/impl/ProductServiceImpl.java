package com.poly.be_duan.service.impl;

import com.poly.be_duan.entities.Color;
import com.poly.be_duan.entities.Product;
import com.poly.be_duan.repositories.ProductRepository;
import com.poly.be_duan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

//    @Override
//    public Product getProductByID(Integer id_products) {
//        return productRepository.findProductById_products(id_products);
//    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {

        productRepository.deleteById(id);
    }
}
