package com.poly.be_duan.beans;

import com.poly.be_duan.validations.users.UniqueUserEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerBean {
    @NotBlank(message = "{User.fullName.NotBlank}")
    @Length(max = 100, message = "{User.fullName.Length}")
    private String fullname;

    @NotBlank(message = "{User.email.NotBlank}")
    @Length(max = 255, message = "{User.email.Length}")
    @Email(message = "{User.email.Email}")
    @UniqueUserEmail(message = "{User.email.UniqueUserEmail}")
    private String email;

    @NotBlank(message = "{User.phone.NotBlank}")
    @Length(min = 10, max = 20, message = "{User.phone.Length}")
    private String phone;

    @NotNull(message = "{User.sex.NotNull}")
    @Min(value = 0)
    @Max(value = 1)
    private int sex;
}
