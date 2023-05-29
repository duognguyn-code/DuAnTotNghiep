package com.poly.be_duan.beans;

import com.poly.be_duan.entities.VariantValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariantValueViewSaleBean {
    private List<VariantValue> variantValues;
    private Integer countSale;
    private List<String> saleName;
}
