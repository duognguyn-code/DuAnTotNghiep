package com.poly.be_duan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "product_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_products_detail")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_size", referencedColumnName = "id_size")
    private Size size;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_color", referencedColumnName = "id_colors")
    private Color color;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_design", referencedColumnName = "id_designs")
    private Designs design;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_material", referencedColumnName = "id_materials")
    private Material material;

    @OneToMany(mappedBy = "productDetail")
    @JoinColumn(name = "id_cart_detail", referencedColumnName = "id_cart_details")
    private List<Cart_detail> cartDetails;
}
