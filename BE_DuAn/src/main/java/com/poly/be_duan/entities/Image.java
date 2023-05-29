package com.poly.be_duan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "images_id", unique = true, nullable = false, length = 50)
    private String imagesId;
    @Column(name = "image_path", nullable = false, length = 255)
    private String imagePath;
    @Column(name = "status", nullable = false, precision = 10)
    private int status;
    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariants;
}
