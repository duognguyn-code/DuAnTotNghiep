package com.poly.be_duan.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "change_product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_change_product")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_bills", referencedColumnName = "id_bills")
    private Bill bill;

    @Column(name = "date_change")
    private LocalDateTime dateChange;

    @Column(name = "description")
    private String description;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String eamil;

    @ManyToOne
    @JoinColumn(name = "id_bill_detail", referencedColumnName = "id_bill_detail")
    private Bill_detail billDetail;

    @Column(name = "quantity_product_change")
    private Integer quantityProductChange;

    @OneToMany(mappedBy = "changeProduct")
    private List<ChangeProductDetail> changeProductDetails;
}
