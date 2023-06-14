package com.poly.be_duan.service;

import com.poly.be_duan.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService extends GenericService<Product, Integer> {

  
    public List<Product> getByColor(String color, String design, String material, String size);


    Page<Product> getAll (Pageable page);

}
