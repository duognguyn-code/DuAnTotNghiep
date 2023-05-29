package com.poly.be_duan.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = "{AuthRequest.username.NotBlank}")
    @Email(message = "{AuthRequest.username.Email}")
    private String username;

    @NotBlank(message = "{AuthRequest.password.NotBlank}")
    private String password;
}
