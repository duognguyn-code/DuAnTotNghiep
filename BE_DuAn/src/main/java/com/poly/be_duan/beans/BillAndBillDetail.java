package com.poly.be_duan.beans;

import com.poly.be_duan.entities.Bill;
import com.poly.be_duan.entities.BillDetail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BillAndBillDetail {
    @NotNull
    private Bill bill;

    @NotEmpty
    private List<BillDetail> billDetails;
}
