package com.poly.be_duan.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Indexed;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "status", nullable = false, precision = 10)
    private int status;

    @Column(name = "image_name")
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Bill_detail> billDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Product_detail> productDetails;

//    @Transient
//    public void addImage(Image image) {
//        if (images == null) {
//            images = new ArrayList<>();
//        }
//        images.add(image);
//        image.setProducts(this);
//    }
}
