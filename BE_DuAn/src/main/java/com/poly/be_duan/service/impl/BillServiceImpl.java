package com.poly.be_duan.service.impl;


import com.poly.be_duan.entities.Bill;
import com.poly.be_duan.repositories.BillRepository;
import com.poly.be_duan.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Override
    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> getBill(Integer id) {
        return billRepository.getBill(id);
    }

    @Override
    public Bill update(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> searchByPhoneAndDateAndStatus(String phone, Date date,Date date1, Integer sts) {
        return billRepository.searchByPhoneAndDateAndStatus(phone,date,date1,sts);
    }

    @Override
    public List<Bill> searchByPhoneAndDate(String phone, Date date,Date date1) {
        return billRepository.searchByPhoneAndDate(phone,date,date1);
    }

    @Override
    public List<Bill> searchByPhoneAndStatus(String phone, Integer sts) {
        return billRepository.searchByPhoneAndStatus(phone,sts);
    }

//    @Override
//    public List<Bill> searchByPhone(String phone) {
//        return billRepository.searchByPhone(phone);
//    }
//
//    @Override
//    public Bill updateStatus(Integer sts, Integer id) {
//        return billRepository.UpdateStatus(sts,id);
//    }

    @Override
    public Bill updateStatus(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Optional<Bill> findBillByID(Integer id) {
        return billRepository.findBillById(id);
    }

//    @Override
//    public List<Bill> /searchByDate(Date date, Date date1) {
//        return billRepository.searchByDate(date,date1);
//    }
}
