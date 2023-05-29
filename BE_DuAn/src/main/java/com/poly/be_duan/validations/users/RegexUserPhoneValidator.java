package com.poly.be_duan.validations.users;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUserPhoneValidator implements ConstraintValidator<RegexUserPhone, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("[0-9]{10,20}$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
