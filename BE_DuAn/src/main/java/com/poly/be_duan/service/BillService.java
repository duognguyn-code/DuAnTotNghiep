package com.poly.be_duan.service;

import com.poly.be_duan.entities.Bill;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BillService {

    List<Bill> getAll();

    public List<Bill> getBill(Integer id);

    public Bill update(Bill bill);

    public List<Bill> searchByPhoneAndDateAndStatus(String phone, Date date, Integer sts);
    public List<Bill> searchByPhoneAndDate(String phone, Date date);
    public List<Bill> searchByPhoneAndStatus(String phone,Integer sts);
    public List<Bill> searchByPhone(String phone);

//    public Bill updateStatus(Integer sts,Integer id);

    public Bill updateStatus(Bill bill);
    public Optional<Bill> findBillByID(Integer id);
}
