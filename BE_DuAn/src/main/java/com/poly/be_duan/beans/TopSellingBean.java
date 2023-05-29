package com.poly.be_duan.beans;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TopSellingBean {
    @NotBlank
    private String type;
    @NotNull
    private int top;
    @NotBlank
    private String typeDate;
}
