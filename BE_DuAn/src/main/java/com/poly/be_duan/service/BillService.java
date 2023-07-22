package com.poly.be_duan.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Bill;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BillService  {

    Bill save(Bill entity);
    List<Bill> getAll();

    Optional<Bill> findById(Integer id);
    public List<Bill> getBill(Integer id);

    public Bill update(Bill bill, Integer id);

    public Bill update(Bill bill);

    public List<Bill> searchByPhoneAndDateAndStatus(String phone, Date date,Date date1, Integer sts);
    public List<Bill> searchByPhoneAndDate(String phone, Date date,Date date1);
    public List<Bill> searchByPhoneAndStatus(String phone,Integer sts);
//    public List<Bill> searchByPhone(String phone);

//    public Bill updateStatus(Integer sts,Integer id);

    public Bill updateStatus(Bill bill);
    public Optional<Bill> findBillByID(Integer id);

//    public List<Bill> searchByDate(Date date, Date date1);
    Bill create(JsonNode billData);

    List<Bill> findAllByAccount(Account account);
}
