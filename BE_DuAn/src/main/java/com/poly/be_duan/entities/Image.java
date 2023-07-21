package com.poly.be_duan.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @Column(name = "id_image")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idimage;

    @Column(name = "url_image", nullable = false, length = 255)
    private String urlimage;

    @ManyToOne
    @JoinColumn(name = "id_productChange")
    private ProductChange productChange;

    @ManyToOne
    @JoinColumn(name = "id_products")
    @JsonBackReference
    private Product products;

//    @JsonBackReference
    public ProductChange getProductChange(){
        return productChange;
    }

}
