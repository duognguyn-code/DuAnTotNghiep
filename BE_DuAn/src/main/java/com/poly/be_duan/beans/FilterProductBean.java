package com.poly.be_duan.beans;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FilterProductBean {
    List<OptionValue> optionValues;
    BigDecimal min;
    BigDecimal max;
}
