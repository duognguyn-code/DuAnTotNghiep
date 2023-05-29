package com.poly.be_duan.beans;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;
@Data
public class ProductVariansBean {
    @Valid
    private List<ProductVariant> productVariants;
    private List<ProductVariant> productVariantsOld;
    private int userId;
}
