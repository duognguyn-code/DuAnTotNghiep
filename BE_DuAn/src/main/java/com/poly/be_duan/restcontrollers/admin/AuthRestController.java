package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.dto.LoginDTO;
import com.poly.be_duan.dto.SignUpDTO;
import com.poly.be_duan.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
        ResponseEntity<?> response = authService.authenticateUser(loginDTO);


            // Đăng nhập thất bại, trả về response như cũ
            return response;


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
}
