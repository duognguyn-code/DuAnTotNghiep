package com.poly.be_duan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bills")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_take")
    private String phoneTake;

    @Column(name = "person_take")
    private String personTake;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "time_receive")
    private LocalDateTime timeReceive;

    @Column(name = "total_money")
    private BigDecimal totalMoney;

    @Column(name = "money_ship")
    private BigDecimal moneyShip;

    @Column(name = "type_payment")
    private Integer typePayment;

    @Column(name = "description")
    private String description;

    @Column(name = "status_buy")
    private Integer statusBuy;

    @Column(name = "status")
    private Integer status;

    @OneToOne
    @JoinColumn(name = "id_bill_old")
    private Bill oldBill;

    @OneToMany(mappedBy = "bill")
    private List<Bill_detail> billDetails;

    @OneToMany(mappedBy = "bill")
    private List<Sale_detail> saleDetails;
}
