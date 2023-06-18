package com.poly.be_duan.service.impl;

import com.poly.be_duan.entities.Product;
import com.poly.be_duan.repositories.ProductRepository;
import com.poly.be_duan.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productDetailRepository;

    public ProductServiceImpl(ProductRepository productDetailRepository1) {
        this.productDetailRepository = productDetailRepository1;
    }

    public List<Product> getByColor(String color, String design, String material, String size) {
        return (List<Product>) productDetailRepository.getByColor(color,design,material,size);
    }



    @Override
    public Page<Product> getAll(Pageable page) {
        return productDetailRepository.findAll(page);
    }

    @Override
    public List<Product> search(String name, String color, String material, String size, String design,BigDecimal min,BigDecimal max, Integer status) {
        return  productDetailRepository.search(name,color,material,size,design,min,max,status);
    }

    @Override
    public BigDecimal searchPriceMin() {
        return productDetailRepository.searchMin();
    }

    @Override
    public BigDecimal searchPriceMAX() {
        return productDetailRepository.searchMax();
    }

    @Override
    public Product save(Product entity) {
        return productDetailRepository.save(entity);
    }

    @Override
    public List<Product> save(List<Product> entities) {
        return (List<Product>) productDetailRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {

        productDetailRepository.deleteById(id);
    }


    @Override
    public Optional<Product> findById(Integer id) {
        return productDetailRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productDetailRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        Page<Product> entityPage = productDetailRepository.findAll(pageable);
        List<Product> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Product update(Product entity, Integer id) {
        Optional<Product> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

}
