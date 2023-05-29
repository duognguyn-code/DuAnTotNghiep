package com.poly.be_duan.validations.users;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserPhoneValidator implements ConstraintValidator<UniqueUserPhone, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
