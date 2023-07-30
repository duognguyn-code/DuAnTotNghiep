package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.entities.Account;
import com.poly.be_duan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Account>getAll(){
        return accountService.getAll();
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

    @GetMapping("/findByPhone/{phone}")
    public Account findByPhone(@PathVariable(value = "phone")String phone){
        System.out.println(accountService.findByPhone(phone));
        return accountService.findByPhone(phone);
    }
}

