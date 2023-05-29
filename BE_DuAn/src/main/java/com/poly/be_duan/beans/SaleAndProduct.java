package com.poly.be_duan.beans;

import com.poly.be_duan.entities.Product;
import com.poly.be_duan.entities.Sale;
import lombok.Data;

import java.util.List;
@Data
public class SaleAndProduct {
    private Sale sale;
    private List<Product> listProduct;
}
