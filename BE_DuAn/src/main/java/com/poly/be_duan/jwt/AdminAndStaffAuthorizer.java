package com.poly.be_duan.jwt;


import org.springframework.security.core.Authentication;

public interface AdminAndStaffAuthorizer {
    boolean authorize(Authentication authentication);
}