package com.poly.be_duan.beans;

import com.poly.be_duan.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailViewBean {
    private ProductVariant productVariant;
    private long quantity;
    private BigDecimal price;
    private BigDecimal tax;
    private BigDecimal totalMoney;
    private User userConfirm;
    private String note;
}
