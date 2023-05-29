package com.poly.be_duan.beans;

import com.poly.be_duan.entities.BillDetail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BillDetailReturnBean {
    @NotNull
    private BillDetail billDetail;
    @NotNull
    private List<Integer> returnTypes;
    @NotBlank
    private List<BillDetail> billDetails;
}
