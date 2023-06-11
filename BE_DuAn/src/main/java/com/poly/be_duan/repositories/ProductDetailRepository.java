package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Product_detail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends PagingAndSortingRepository<Product_detail, Integer> {
}
