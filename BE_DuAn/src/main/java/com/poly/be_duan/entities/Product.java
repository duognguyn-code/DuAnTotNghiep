package com.poly.be_duan.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    protected static final String PK = "productId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_products", unique = true, nullable = false, precision = 10)
    private Integer id_products;


    @Column(name = "name", nullable = false, length = 100)
    private String name;


    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "status", nullable = false, precision = 10)
    private int status;

    @Column(name = "description")
    private String description;


    @OneToMany(mappedBy = "product")
    private List<Image> images;
}
