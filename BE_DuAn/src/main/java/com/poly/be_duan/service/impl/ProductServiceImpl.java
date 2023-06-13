package com.poly.be_duan.service.impl;

import com.poly.be_duan.entities.Product;
import com.poly.be_duan.repositories.ProductRepository;
import com.poly.be_duan.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productDetailRepository;

    public ProductServiceImpl(ProductRepository productDetailRepository1) {
        this.productDetailRepository = productDetailRepository1;
    }

    @Override
    public List<Product> getAll() {
        return  productDetailRepository.findAll();
    }

    public List<Product> getByColor(String color, String design, String material, String size) {
        return (List<Product>) productDetailRepository.getByColor(color,design,material,size);
    }


    @Override
    public Product create(Product product) {
        return productDetailRepository.save(product);
    }

    @Override
    public Page<Product> getAll(Pageable page) {
        return null;
    }

    @Override
    public List<Product> save(List<Product> entities) {
        return (List<Product>) productDetailRepository.saveAll(entities);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productDetailRepository.findById(id);
    }


    @Override
    public Product update(Product product, Integer id) {
        Optional<Product> optional = findById(id);
        if (optional.isPresent()) {
            return create(product);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
