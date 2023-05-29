package com.poly.be_duan.beans;

import com.poly.be_duan.entities.Sale;
import lombok.Data;

import java.util.List;
@Data
public class SaleAndSaleChild {
    Sale saleParent;
    List<Sale> lstSaleChild;
    List<ProductVariant> listProductVariants;
}
