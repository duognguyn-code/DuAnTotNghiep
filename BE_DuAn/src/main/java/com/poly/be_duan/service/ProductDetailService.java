package com.poly.be_duan.service;

import com.poly.be_duan.entities.Product;
import com.poly.be_duan.entities.Product_detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductDetailService {
    public List<Product_detail> getAll();

    public Product_detail create(Product_detail product_detail);

    Page<Product_detail> getAll (Pageable page);

    List<Product_detail> save(List<Product_detail> entities);
//    public Product getProductByID(Integer id);

    Optional<Product_detail> findById(Integer id);
    public Product_detail update(Product_detail product_detail, Integer id);

    public void delete(Integer id);
}
