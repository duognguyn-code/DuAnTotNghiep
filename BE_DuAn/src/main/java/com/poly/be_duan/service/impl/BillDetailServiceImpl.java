package com.poly.be_duan.service.impl;


import com.poly.be_duan.entities.Bill_detail;
import com.poly.be_duan.repositories.BillDetailRepository;
import com.poly.be_duan.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return billDetailRepository.save(bill_detail);
    }
    @Override
    public List<Bill_detail> getBill_detailForMoney(int id) {
        return billDetailRepository.getBill_detailForMoney(id);
    }


}
