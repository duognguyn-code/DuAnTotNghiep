package com.poly.be_duan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.be_duan.validations.users.RegexUserPhone;
import com.poly.be_duan.validations.users.UniqueUserEmail;
import com.poly.be_duan.validations.users.UniqueUserPhone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "users", indexes = { @Index(name = "users_Email_IX", columnList = "email", unique = true),
        @Index(name = "users_Phone_IX", columnList = "phone", unique = true) })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable, UserDetails {
    protected static final String PK = "userId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false, precision = 10)
    private int userId;

    @NotBlank(message = "{User.fullName.NotBlank}")
    @Length(max = 100, message = "{User.fullName.Length}")
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @NotBlank(message = "{User.email.NotBlank}")
    @Length(max = 255, message = "{User.email.Length}")
    @Email(message = "{User.email.Email}")
    @UniqueUserEmail(message = "{User.email.UniqueUserEmail}")
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @NotBlank(message = "{User.password.NotBlank}")
    @Length(min = 8, max = 100, message = "{User.password.Length}")
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @NotBlank(message = "{User.phone.NotBlank}")
    @Length(min = 10, max = 20, message = "{User.phone.Length}")
    @RegexUserPhone(message = "{User.phone.RegexUserPhone}")
    @UniqueUserPhone(message = "{User.phone.UniqueUserPhone}")
    @Column(name = "phone", unique = true, nullable = false, length = 20)
    private String phone;

    @NotNull(message = "{User.sex.NotNull}")
    @Min(value = 0)
    @Max(value = 1)
    @Column(name = "sex", nullable = false, precision = 10)
    private int sex;

    @NotNull(message = "{User.role.NotNull}")
    @Column(name = "role", nullable = false, precision = 10)
    private int role;

    @Column(name = "avatar", length = 200)
    private String avatar;

    @JsonIgnore
    @NotNull(message = "{User.otp.NotNull}")
    @Column(name = "otp", nullable = false, precision = 10)
    private int otp;

    @NotNull(message = "{User.status.NotNull}")
    @Column(name = "status", nullable = false, precision = 10)
    private int status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @JsonIgnore
    @OneToMany(mappedBy = "userCreate")
    private Set<ProductVariant> productVariants;

    @JsonIgnore
    @OneToMany(mappedBy = "userEdit")
    private Set<ProductVariant> productVariants2;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private Set<Cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Bill> bills;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Bill> bill2;

    @JsonIgnore
    @OneToMany(mappedBy = "userEdit")
    private Set<Setting> settings;

    @JsonIgnore
    @OneToMany(mappedBy = "userConfirm")
    private Set<BillDetail> billDetails;

    @Column(name = "division_id", nullable = false, precision = 10)
    private int divisionId;

    @Column(name = "division_name", nullable = false, length = 100)
    private String divisionName;

    @Column(name = "district_id", nullable = false, precision = 10)
    private int districtId;

    @Column(name = "district_name", nullable = false, length = 100)
    private String districtName;

    @Column(name = "ward_name", nullable = false, length = 100)
    private String wardName;

    @Column(name = "ward_code", nullable = false, length = 45)
    private String wardCode;

    @Column(name = "address_detail", length = 255)
    private String address;
    private boolean equalKeys(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User that = (User) other;
        if (this.getUserId() != that.getUserId()) {
            return false;
        }
        return true;
    }

    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<>(6);
        ret.put("userId", Integer.valueOf(getUserId()));
        return ret;
    }

    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getUserId();
        result = 37 * result + i;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof User))
            return false;
        return this.equalKeys(other) && ((User) other).equalKeys(this);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Users |");
        sb.append(" userId=").append(getUserId());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
