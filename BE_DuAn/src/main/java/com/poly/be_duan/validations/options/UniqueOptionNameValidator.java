package com.poly.be_duan.validations.options;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueOptionNameValidator implements ConstraintValidator<UniqueOptionName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
