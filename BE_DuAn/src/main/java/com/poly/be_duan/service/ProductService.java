package com.poly.be_duan.service;

import com.poly.be_duan.dto.ProductDetailDTO;
import com.poly.be_duan.dto.ProductResponDTO;
import com.poly.be_duan.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService extends GenericService<Product, Integer> {

  
    public List<Product> getByColor(String color, String design, String material, String size);


    Page<Product> getAll (Pageable page);
    List<Product> search(String name, String color, String material, String size, String design, BigDecimal min, BigDecimal max, Integer status);
    BigDecimal searchPriceMin();
    BigDecimal searchPriceMAX();
    ProductDetailDTO getDetailProduct(Integer id);
    List<ProductResponDTO> findByCategoryAndStatus(Integer id);
}
