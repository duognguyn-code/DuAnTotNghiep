package com.poly.be_duan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponDTO {
    private String username;
    private String fullName;
    private Integer gender;
    private Integer status;
    private String password;
    private String email;
    private String phone;
    private String image;
    private Set<String> role;
    private Date createDate;
}
