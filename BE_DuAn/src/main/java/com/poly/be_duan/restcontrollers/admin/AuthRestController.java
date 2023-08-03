package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.dto.LoginDTO;
import com.poly.be_duan.dto.SignUpDTO;
import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Author;
import com.poly.be_duan.service.AuthService;
import com.poly.be_duan.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) throws Exception {
        ResponseEntity<?> response = authService.authenticateUser(loginDTO);
        if (response.getStatusCode() == HttpStatus.OK) {
            // Đăng nhập thành công, trả về URL của trang chính
//            String mainPageUrl = "http://localhost:/admin/adminUser/Login.html"; // Thay đổi đường dẫn này cho phù hợp với URL của trang chính
            return ResponseEntity.ok("alo");
        } else {
            // Đăng nhập thất bại, trả về response như cũ
            return response;
        }


    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO) {
        ResponseEntity<?> response =  authService.registerUser(signUpDTO);
        if (response.getStatusCode() == HttpStatus.OK) {
            // Đăng nhập thành công, trả về URL của trang chính
//            String mainPageUrl = "http://localhost:/admin/adminUser/Login.html"; // Thay đổi đường dẫn này cho phù hợp với URL của trang chính
            return ResponseEntity.ok("alo");
        } else {
            // Đăng nhập thất bại, trả về response như cũ
            return response;
        }
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        System.out.println(author.getAccount().getUsername());
        System.out.println(author.getRole().getIdRole());
        System.out.println(author.toString());

        return authService.save(author);
    }

    @GetMapping
    public List<Author> getAccount(){
        return authService.findAll();
    }
}
