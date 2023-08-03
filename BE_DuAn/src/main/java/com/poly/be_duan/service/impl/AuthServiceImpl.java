package com.poly.be_duan.service.impl;

import com.poly.be_duan.config.ERole;
import com.poly.be_duan.dto.LoginDTO;
import com.poly.be_duan.dto.SignUpDTO;
import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Author;
import com.poly.be_duan.entities.Role;
import com.poly.be_duan.jwt.JwtResponse;
import com.poly.be_duan.jwt.JwtTokenProvider;
import com.poly.be_duan.repositories.AccountRepository;
import com.poly.be_duan.repositories.AuthorRepository;
import com.poly.be_duan.repositories.RoleRepository;
import com.poly.be_duan.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final AuthorRepository authorRepository;
    @Override
    public ResponseEntity<?> registerUser(SignUpDTO signUpDTO) {
        if(accountRepository.existsAccountByUsername(signUpDTO.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        if (accountRepository.existsAccountByEmail(signUpDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }
        Account ac = new Account();
        ac.setUsername(signUpDTO.getUsername());
        ac.setEmail(signUpDTO.getEmail());
        ac.setPhone(signUpDTO.getPhone());
        ac.setPassword(passwordEncoder.encode(signUpDTO.getPhone()));
        ac.setFullName(signUpDTO.getFullName());
        ac.setDate(new Date());
        ac.setStatus(1);
        accountRepository.save(ac);
        return ResponseEntity.ok("User registered successfully1");
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginDTO loginDTO) {
        System.out.println(loginDTO.getUsername() + loginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));

        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        System.out.println(jwt);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        System.out.println(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        System.out.println(roles + "của user");
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(),userDetails.getEmail(),roles));
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
