package com.poly.be_duan.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankBean {
    @NotBlank(message = "{BankBean.accountNumber.NotBlank}")
    private String accountNumber;

    @NotBlank(message = "{BankBean.bankName.NotBlank}")
    private String bankName;

    @NotBlank(message = "{BankBean.accountHolder.NotBlank}")
    private String accountHolder;
}
