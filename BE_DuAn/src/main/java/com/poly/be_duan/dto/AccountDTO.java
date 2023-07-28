package com.poly.be_duan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements Serializable {
    private String username;
    private String fullName;
    private Integer gender;
    private String email;
    private String phone;
    private String image;
    private String role;
    private String password;
    private Date date;
}
