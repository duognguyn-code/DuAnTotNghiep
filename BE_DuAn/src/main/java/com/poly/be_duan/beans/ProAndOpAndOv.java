package com.poly.be_duan.beans;

import com.poly.be_duan.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProAndOpAndOv {
    private Product product;
    private List<Option> options;
    private List<OptionValue> optionValues;
}
