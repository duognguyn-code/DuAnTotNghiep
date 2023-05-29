package com.poly.be_duan.beans;

import com.poly.be_duan.entities.Bill;
import lombok.Data;

@Data
public class StatusBillBean {
    private Bill bill;
    private int status;
}
