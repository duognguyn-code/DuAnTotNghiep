package com.poly.be_duan.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "username", length = 250)
    @NotBlank(message = "Tên tài khoản không được trống")
    @Size(min = 3, max = 250, message = "Tên tài khoản từ 3-250 ký tự")
    private String username;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Integer sex;

    @Column(name = "address_id", nullable = false)
    private String addressId;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "INT DEFAULT 1")
    private Integer status;

    @OneToMany(mappedBy = "account")
    private List<Bill> bills;
}
