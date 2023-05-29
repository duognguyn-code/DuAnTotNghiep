package com.poly.be_duan.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class VariantValuesPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "Option_id", nullable = false),
            @JoinColumn(name = "Product_id", nullable = false)
    })
    private ProductOption  productOptions;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Variant_id", nullable = false)
    private ProductVariant productVariants;
}
