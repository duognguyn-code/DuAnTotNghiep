package com.poly.be_duan.service.impl;

import com.poly.be_duan.dto.ProductDetailDTO;
import com.poly.be_duan.dto.ProductResponDTO;
import com.poly.be_duan.entities.Product;
import com.poly.be_duan.entities.ProductChange;
import com.poly.be_duan.repositories.ProductChangeRepository;
import com.poly.be_duan.service.ProductChangeService;
import com.poly.be_duan.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductChangeServiceImpl implements ProductChangeService {
    private final ProductChangeRepository productChangeRepository;


    @Override
    public ProductChange save(ProductChange entity) {
        return productChangeRepository.save(entity);
    }

    @Override
    public List<ProductChange> save(List<ProductChange> entities) {
        return productChangeRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {

        productChangeRepository.deleteById(id);
    }

    @Override
    public Optional<ProductChange> findById(Integer id) {
        return productChangeRepository.findById(id);
    }

    @Override
    public List<ProductChange> findAll() {
        return null;
    }

    @Override
    public Page<ProductChange> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ProductChange update(ProductChange entity, Integer id) {
        Optional<ProductChange> optional = findById(id) ;
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public List<ProductChange> listProductChange() {
        return productChangeRepository.findProductChange();
    }

    @Override
    public List<ProductChange> findAllProductChange() {
        return productChangeRepository.findAllProductChange();
    }

    @Override
    public ProductChange findByStatus(Integer idPrChange) {
        if(idPrChange != null){
            return productChangeRepository.findByStatus(idPrChange);
        }return null;
    }

    @Override
    public List<ProductChange> findByUsername(String username) {
        if(username != null){
            return productChangeRepository.findByAccount(username);
        }
        return null;
    }

    @Override
    public List<ProductChange> findByStatusSendEmail(Integer idChange) {
        return productChangeRepository.findByStatusSendEmail(idChange);
    }
}
