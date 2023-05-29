package com.poly.be_duan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "CartDetail")
@Table(name = "cart_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDetail implements Serializable {
    protected static final String PK = "cartDetailId";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cart_detail_id", unique = true, nullable = false, length = 255)
    private String cartDetailId;

    @NotNull
    @Min(value = 1)
    @Column(nullable = false, precision = 10)
    private int quantity;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart carts;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "variant_id", nullable = false)
    private ProductVariant productVariants;
}
