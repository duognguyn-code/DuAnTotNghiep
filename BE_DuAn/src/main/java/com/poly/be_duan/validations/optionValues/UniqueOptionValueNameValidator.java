package com.poly.be_duan.validations.optionValues;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueOptionValueNameValidator implements ConstraintValidator<UniqueOptionValueName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
