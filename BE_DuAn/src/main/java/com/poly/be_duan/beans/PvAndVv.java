package com.poly.be_duan.beans;

import com.poly.be_duan.entities.VariantValue;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PvAndVv {
    @NotNull
    @Valid
    private ProductVariant productVariant;

    @NotEmpty
    private List<VariantValue> variantValues;

}
