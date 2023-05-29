package com.poly.be_duan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bills")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bills")
    private Long id_bills;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Account account;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_take", nullable = false)
    private String phoneTake;

    @Column(name = "person_take", nullable = false)
    private String personTake;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "time_receive", nullable = false)
    private LocalDateTime timeReceive;

    @Column(name = "total_money")
    private BigDecimal totalMoney;

    @Column(name = "money_ship")
    private BigDecimal moneyShip;

    @Column(name = "type_payment", nullable = false)
    private Integer typePayment;

    @Column(nullable = false)
    private String description;

    @Column(name = "status_buy", columnDefinition = "INT DEFAULT 1")
    private Integer statusBuy;

    @Column(columnDefinition = "INT DEFAULT 1")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_bill_old")
    private Bill oldBill;
}
