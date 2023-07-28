package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.dto.AccountDTO;
import com.poly.be_duan.dto.AccountRequestDTO;
import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Role;
import com.poly.be_duan.service.AccountService;
import com.poly.be_duan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    @Autowired
    AccountService accountService;

    @Autowired
    private RoleService roleService;


    @PostMapping
    public Account create(@RequestBody Account account) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = today.format(dateTimeFormatter);
        Date dates1 = new Date(date);
        account.setDate(dates1);
        account.setStatus(1);
        return accountService.save(account);
    }

    @GetMapping
    public List<AccountDTO> getAll(){
        return accountService.getAllAccountResponseDTO();
    }

    @GetMapping("/getAllrole")
    public List<Role> getAllRole(){
        return roleService.getAll();
    }
    @PostMapping("/create")
    public ResponseEntity<?> registerUser(AccountRequestDTO accountDTO) {
        ResponseEntity<?> response =  accountService.save(accountDTO);
        if (response.getStatusCode() == HttpStatus.OK) {
            // Đăng nhập thành công, trả về URL của trang chính
            return ResponseEntity.ok("Tạo mới thành công");
        } else {
            // Đăng nhập thất bại, trả về response như cũ
            return response;
        }
    }

    @GetMapping("/findByUsername/{username}")
    public Account findByUsername(@PathVariable(value = "username")String username){
        return accountService.findByUsername(username);
    }
    @PutMapping
    public Account update(@RequestBody Account account){

        return accountService.save(account);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable(value = "username")String username){
         accountService.deleteById(username);
    }
}

