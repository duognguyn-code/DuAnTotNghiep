package com.poly.be_duan.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
public class PasswordBean {
    @NotBlank(message = "{PasswordBean.passwordOld.NotBlank}")
    private String passwordOld;
    @NotBlank(message = "{PasswordBean.passwordNew.NotBlank}")
    @Length(min = 8,message = "{PasswordBean.passwordNew.Length}")
    private String passwordNew;
    @NotBlank(message = "{PasswordBean.confirmPassword.NotBlank}")
//	@Length(min = 8,message = "{PasswordBean.confirmPassword.Length}")
    private String confirmPassword;
}
