package com.poly.be_duan.beans;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmailBean {
    @NotBlank(message = "{EmailBean.email.NotBlank}")
    @Email(message = "{EmailBean.email.Email}")
    private String email;

    @NotBlank(message = "{EmailBean.password.NotBlank}")
    private String password;
}
