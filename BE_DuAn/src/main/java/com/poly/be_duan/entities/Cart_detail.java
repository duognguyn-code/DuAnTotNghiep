package com.poly.be_duan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart_details")
    private Integer id;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_cart", referencedColumnName = "id_carts")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "id_product_detail", referencedColumnName = "id_product_details")
    private Product_detail productDetail;
}
