package com.poly.be_duan.service;

import com.poly.be_duan.dto.LoginDTO;
import com.poly.be_duan.dto.SignUpDTO;
import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {
    ResponseEntity<?> registerUser(SignUpDTO SignUpDTO);
    ResponseEntity<?> authenticateUser(LoginDTO loginDTO);

    Author save(Author author);

    List<Author>findAll();
}
