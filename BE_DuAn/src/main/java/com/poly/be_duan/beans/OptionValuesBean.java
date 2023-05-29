package com.poly.be_duan.beans;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class OptionValuesBean {
    @Valid
    private List<OptionValue> listOptionValues;
}
