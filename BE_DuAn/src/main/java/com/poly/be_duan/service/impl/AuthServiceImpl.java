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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final AuthorRepository authorRepository;
    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<?> registerUser(SignUpDTO signUpDTO) {
        if(accountRepository.existsAccountByUsername(signUpDTO.getUserName())){
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
        ac.setUsername(signUpDTO.getUserName());
        ac.setFullName(signUpDTO.getFullName());
        ac.setPhone(signUpDTO.getPhone());
        ac.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
//        ac.setDate(signUpDTO.getDate());
        ac.setSex(signUpDTO.getSex());
        ac.setEmail(signUpDTO.getEmail());
        ac = accountRepository.save(ac);
        Set<String> strRole = signUpDTO.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRole == null || strRole.isEmpty()) {
            // Nếu không có vai trò được chỉ định, mặc định là vai trò ROLE_USER
            Role defaultRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: role not found"));
            roles.add(defaultRole);
        } else {
            for (String role : strRole) {
                Role foundRole = roleRepository.findByName(ERole.ROLE_STAFF)
                        .orElseThrow(() -> new RuntimeException("Error: role not found"));
                roles.add(foundRole);
            }
        }
        if(strRole == null){
            Role accountRole =  roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("ErrorL: Role is not found "));
            roles.add(accountRole);
        }else {
            strRole.forEach(role -> {
                switch (role){
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: role is not found"));
                        roles.add(adminRole);
                        break;
                    case "user":
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(("Error: role not found")));
                        roles.add(userRole);
                        break;
                    case "staff":
                        Role staffRole = roleRepository.findByName(ERole.ROLE_STAFF)
                                .orElseThrow(() -> new RuntimeException(("Error: role not found")));
                        roles.add(staffRole);
                        break;
                    case "guest":
                        Role guestRole = roleRepository.findByName(ERole.ROLE_GUEST)
                                .orElseThrow(() -> new RuntimeException(("Error: role not found")));
                        roles.add(guestRole);
                        break;
                }
            });
        }
        List<Author> authorList = new ArrayList<>();
        for (Role role: roles) {
            Author author = new Author();
            author.setAccount(accountRepository.getById(ac.getUsername()));
            author.setRole(role);
            authorList.add(author);
        }
        ac.setAuthorList(authorList);
        authorList = authorRepository.saveAll(authorList);
        return ResponseEntity.ok("User registered successfully1");
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

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

    @Override
    public List<Author> searchAllAccount(String username, String phone, String fullName, String email, Integer status, String roldeName) {
        return authorRepository.searchAllAccount(username,phone, fullName, email, status, roldeName);
    }

    @Override
    public List<Author> searchAccountNoStatus(String username, String phone, String fullName, String email, String roldeName) {
        return authorRepository.searchAccountNoStatus(username, phone, fullName, email,roldeName);
    }

    @Override
    public List<Author> searchAccountNoUsername(String phone, String fullName, String email, Integer status, String roldeName) {
        return authorRepository.searchAccountNoUsername(phone,fullName, email, status, roldeName );
    }

    @Override
    public List<Author> searchAccountNoUsernameNoStatus(String phone, String fullName, String email, String roldeName) {
        return authorRepository.searchAccountNoUsernameNoStatus(phone, fullName, email, roldeName);
    }

    @Override
    public Author searchAccountByUsername(String username) {
        return authorRepository.searchAccountByUsername(username);
    }
}
