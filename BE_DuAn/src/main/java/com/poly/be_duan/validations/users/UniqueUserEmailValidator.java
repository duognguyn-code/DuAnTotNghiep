package com.poly.be_duan.validations.users;

import com.poly.be_duan.restcontrollers.admin.UserRestControllerAdmin;
import com.poly.be_duan.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserServiceImpl service;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (this.request == null) {
            return true;
        } else {
            String method = request.getMethod();
            if (method.equalsIgnoreCase("POST")) {
                if (service.findByEmail(value) != null) {
                    return false;
                } else {
                    return true;
                }
            } else {
                String name = UserRestControllerAdmin.getUserStatic().getEmail();
                if (name.equals(value)) {
                    return true;
                } else {
                    if (service.findByEmail(value) != null) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }
}
