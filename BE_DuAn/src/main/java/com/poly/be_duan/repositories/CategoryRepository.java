package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {
    Optional<Category> getCategoryByTypeAndName(Integer type, String name);
}
