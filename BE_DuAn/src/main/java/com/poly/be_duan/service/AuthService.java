package com.poly.be_duan.service;

import com.poly.be_duan.dto.LoginDTO;
import com.poly.be_duan.dto.SignUpDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> registerUser(SignUpDTO signUpDTO);
    ResponseEntity<?> authenticateUser(LoginDTO loginDTO);
}
