package com.poly.be_duan.service.impl;

import com.poly.be_duan.entities.Color;
import com.poly.be_duan.entities.Image;
import com.poly.be_duan.entities.Product;
import com.poly.be_duan.repositories.ProductRepository;
import com.poly.be_duan.service.ImageService;
import com.poly.be_duan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> getAll(Pageable page) {
        Page<Product> entityPage = productRepository.findAll(page);
        List<Product> entities = entityPage.getContent();
        return new PageImpl<>(entities, page, entityPage.getTotalElements());
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
