package com.poly.be_duan.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionAndOptionValue {
    @Valid
    @NotNull
    private Option option;
    @Valid
    private List<OptionValue> optionValues;
}
