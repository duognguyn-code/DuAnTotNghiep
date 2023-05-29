package com.poly.be_duan.beans;

import com.poly.be_duan.entities.Sale;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SaleAndProductVariants {
    @NotNull
    private Sale sale;

    //	@NotEmpty
    private List<ProductVariant> listProductVariants;
}
