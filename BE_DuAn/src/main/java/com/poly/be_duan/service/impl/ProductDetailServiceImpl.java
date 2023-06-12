package com.poly.be_duan.service.impl;

import com.poly.be_duan.entities.Product_detail;
import com.poly.be_duan.repositories.ProductDetailRepository;
import com.poly.be_duan.service.ProductDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository, ProductDetailRepository productDetailRepository1) {
        this.productDetailRepository = productDetailRepository1;
    }

    @Override
    public List<Product_detail> getAll() {
        return (List<Product_detail>) productDetailRepository.findAll();
    }

    public List<Product_detail> getByColor(String color, String design,String material, String size,String product) {
        return (List<Product_detail>) productDetailRepository.getByColor(color,design,material,size,product);
    }


    @Override
    public Product_detail create(Product_detail product_detail) {
        return productDetailRepository.save(product_detail);
    }

    @Override
    public Page<Product_detail> getAll(Pageable page) {
        return null;
    }

    @Override
    public List<Product_detail> save(List<Product_detail> entities) {
        return (List<Product_detail>) productDetailRepository.saveAll(entities);
    }

    @Override
    public Optional<Product_detail> findById(Integer id) {
        return productDetailRepository.findById(id);
    }


    @Override
    public Product_detail update(Product_detail product_detail, Integer id) {
        Optional<Product_detail> optional = findById(id);
        if (optional.isPresent()) {
            return create(product_detail);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
