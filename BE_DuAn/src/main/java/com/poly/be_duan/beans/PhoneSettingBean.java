package com.poly.be_duan.beans;

import com.poly.be_duan.entities.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PhoneSettingBean {
    @NotBlank(message = "{Setting.phoneShop.NotBlank}")
    @Length(max = 100, message = "{Setting.phoneShop.Length}")
    private String phone;

    @NotNull
    private User user;
}
