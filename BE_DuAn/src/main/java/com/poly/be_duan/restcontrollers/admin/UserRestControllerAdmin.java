package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.entities.User;
import com.poly.be_duan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("admin/rest/users")
public class UserRestControllerAdmin {
    @Autowired
    private UserService userService;

    static User userStatic;

    @Autowired
    private MessageSource messageSource;

    public static User getUserStatic() {
        return userStatic;
    }
}
