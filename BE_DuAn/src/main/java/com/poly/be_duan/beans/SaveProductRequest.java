package com.poly.be_duan.beans;

import com.poly.be_duan.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveProductRequest {
    private Integer id;
    private Product product;
    private Size size;
    private Color color;
    private Designs design;
    private Material material;
}
