package com.poly.be_duan.beans;

import com.poly.be_duan.entities.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

public class ProductAndProductOptionBean {
    @Valid
    @NotNull
    private Product product;

    private List<ProductOption> productOptions;
}
