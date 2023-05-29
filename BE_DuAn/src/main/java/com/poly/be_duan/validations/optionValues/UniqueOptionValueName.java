package com.poly.be_duan.validations.optionValues;

import javax.validation.Payload;

public @interface UniqueOptionValueName {
    String message() default "Giá trị đã tồn tại";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
