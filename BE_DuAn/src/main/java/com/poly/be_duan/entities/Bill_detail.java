package com.poly.be_duan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bill_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date_return")
    private LocalDateTime dateReturn;

    @Column(name = "money_refund")
    private BigDecimal moneyRefund;

    @Column(name = "description")
    private String description;

    @Column(name ="status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_bills")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "id_products")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "previous_bill_detail_id")
    private Bill_detail previousBillDetail;
}
