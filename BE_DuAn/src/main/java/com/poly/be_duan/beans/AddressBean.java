package com.poly.be_duan.beans;

import com.poly.be_duan.entities.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddressBean {
    @NotNull
    private int divisionId;

    @NotBlank(message = "{AddressBean.divisionName.NotBlank}")
    private String divisionName;

    @NotNull
    private int districtId;

    @NotBlank(message = "{AddressBean.districtName.NotBlank}")
    private String districtName;

    @NotBlank
    private String wardCode;

    @NotBlank(message = "{AddressBean.wardName.NotBlank}")
    private String wardName;

    private String addressDetail;

    public AddressBean(User u) {
        this.divisionId = u.getDivisionId();
        this.divisionName = u.getDivisionName();
        this.districtId = u.getDistrictId();
        this.districtName = u.getDistrictName();
        this.wardCode = u.getWardCode();
        this.wardName = u.getWardName();
        this.addressDetail = u.getAddress();
    }

    public AddressBean() {
        super();
    }
}
