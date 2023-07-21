package com.poly.be_duan.service.impl;


import com.poly.be_duan.entities.Bill_detail;
import com.poly.be_duan.repositories.BillDetailRepository;
import com.poly.be_duan.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillDetailServiceImpl implements BillDetailService {

    @Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public List<Bill_detail> getAll() {
        return billDetailRepository.findAll();
    }

    @Override
    public List<Bill_detail> getBill_detail(int id) {
        return billDetailRepository.getBill_detail(id);
    }

    @Override
    public Bill_detail save(Bill_detail entity) {
        return billDetailRepository.save(entity);
    }

    @Override
    public Bill_detail update(Bill_detail bill_detail) {
        return null;
    }

    @Override
    public List<Bill_detail> save(List<Bill_detail> entities) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Optional<Bill_detail> findById(Integer id) {
        return billDetailRepository.findById(id);
    }

    @Override
    public List<Bill_detail> findAll() {
        return null;
    }

    @Override
    public Page<Bill_detail> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Bill_detail update(Bill_detail entity, Integer id) {
        Optional<Bill_detail> optional = findById(id) ;
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;

    }
}
