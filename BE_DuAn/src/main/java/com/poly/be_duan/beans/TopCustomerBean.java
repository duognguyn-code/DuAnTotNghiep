package com.poly.be_duan.beans;

import com.poly.be_duan.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TopCustomerBean {
    @Id
    private User user;
    private BigDecimal totalMoney;
}
