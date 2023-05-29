package com.poly.be_duan.beans;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class RevenueOnlineOfflineAllBean {
    private BigDecimal all;
    private BigDecimal online;
    private BigDecimal offline;
}
