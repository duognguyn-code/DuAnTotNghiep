package com.poly.be_duan.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TopProductVariantSelling {
    @Id
    private ProductVariant productVariant;
    private long quantity;
}
