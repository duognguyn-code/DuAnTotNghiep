package com.poly.be_duan.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AboutDaysBean {
    @NotNull(message = "{AboutDaysBean.startDate.NotNull}")
    private LocalDate startDate;
    @NotNull(message = "{AboutDaysBean.endDate.NotNull}")
    private LocalDate endDate;
    private BigDecimal revenue;
}
