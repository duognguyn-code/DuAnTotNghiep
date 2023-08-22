package com.poly.be_duan.dto;

import lombok.Data;

@Data
public class CartItem {
    private String product;
    private String design;
    private String size;
    private String color;
    private String material;
    private int quantity;
}
