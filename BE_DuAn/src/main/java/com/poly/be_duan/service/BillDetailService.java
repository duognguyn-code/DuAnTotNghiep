package com.poly.be_duan.service;

import com.poly.be_duan.entities.Bill_detail;

import java.util.List;

public interface BillDetailService {

    List<Bill_detail> getAll();

    List<Bill_detail> getBill_detail(int id);
}
