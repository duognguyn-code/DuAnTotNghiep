package com.poly.be_duan.validations.products;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueProductNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProductName {
    String message() default "Product Name already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
