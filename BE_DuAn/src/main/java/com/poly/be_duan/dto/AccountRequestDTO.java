package com.poly.be_duan.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Set;

@Data
public class AccountRequestDTO implements Serializable {
    private String username;
    private String fullName;
    private Integer gender;
    private Integer status;
    private String password;
    private String email;
    private String phone;
    private MultipartFile image;
    private Set<String> role;
}
