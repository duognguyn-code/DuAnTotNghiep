package com.poly.be_duan.jwt;

import com.poly.be_duan.entities.User;
import com.poly.be_duan.service.UserService;
import com.poly.be_duan.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;


public class AdminAuthorizerImpl implements AdminAndStaffAuthorizer{
    private final Logger logger = LoggerFactory.getLogger(AdminAuthorizerImpl.class);
    @Autowired
    private UserService userService;

    @Override
    public boolean authorize(Authentication authentication) {
        boolean isAllow = false;
        try {
            UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) authentication;
            if (user == null) {
                return isAllow;
            }
            User account = (User) user.getPrincipal();

            if (account.getUserId() == 0) {
                return isAllow;
            }
            if (userService.findById(account.getUserId()).getRole() == Constant.ROLE_ADMIN_USER) {
                isAllow = true;
            }
//			System.out.println(user.getPrincipal());
        } catch (Exception e) {
            logger.error(e.toString(), e);
            throw e;
        }
        return isAllow;
    }

    // Lay ra securedPath duoc Annotate RequestMapping trong Controller
    private String extractSecuredPath(Object callerObj) {
        Class<?> clazz = ResolvableType.forClass(callerObj.getClass()).getRawClass();
        Optional<Annotation> annotation = Arrays.asList(clazz.getAnnotations()).stream().filter((ann) -> {
            return ann instanceof RequestMapping;
        }).findFirst();
        logger.debug("FOUND CALLER CLASS: {}", ResolvableType.forClass(callerObj.getClass()).getType().getTypeName());
        if (annotation.isPresent()) {
            return ((RequestMapping) annotation.get()).value()[0];
        }
        return null;
    }
}
