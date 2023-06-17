package com.poly.be_duan.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_products", unique = true, nullable = false, precision = 10)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "status", nullable = false, precision = 10)
    private int status;

    @OneToMany(mappedBy = "products")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Image> images;


    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Bill_detail> billDetails;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

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

    @JsonIgnore
    @OneToMany(mappedBy = "productDetail")
    private List<Cart_detail> cartDetails;

}
