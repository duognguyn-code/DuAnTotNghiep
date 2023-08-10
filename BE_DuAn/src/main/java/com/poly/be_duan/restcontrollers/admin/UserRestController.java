package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.dto.AccountDTO;
import com.poly.be_duan.dto.AddressDTO;
import com.poly.be_duan.entities.Account;
import com.poly.be_duan.service.AccountService;
import com.poly.be_duan.service.ProductService;
import com.poly.be_duan.utils.Username;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;

@RestController
@RequestMapping(value= "/rest/user")
@CrossOrigin("*")
@Component
@RequiredArgsConstructor
public class UserRestController {
    private final AccountService accountService;

    @Autowired
    ProductService productSV;

//    @Autowired
//    ServletContext application;

    @Autowired
    ModelMapper modelMapper;

    Account account = null;

    @GetMapping("/getAccountActive")
    public Account getAccountActive() {
        Account account = accountService.findByUsername(Username.getUserName());
        System.out.println(account.getEmail());
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
        return account;

    }

    @GetMapping("/getAddress")
    public AddressDTO getAddress(){
        return accountService.getAddress();
    }

    @PostMapping("/setaddressdefault")
    public void setaddressdefault(@RequestBody Integer id){
        accountService.setAddressDefault(id);
    }
}
