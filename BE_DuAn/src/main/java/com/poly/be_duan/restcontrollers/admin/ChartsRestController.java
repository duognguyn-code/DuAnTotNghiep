package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.service.AccountService;
import com.poly.be_duan.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/chart")
public class ChartsRestController {

    @Autowired
    BillService billService;

    @Autowired
    AccountService accountService;

    @GetMapping("/{year}")
    public List<List> getAll(@PathVariable(value = "year")String year) {
        List a = new ArrayList();
        for (int i = 1; i <=12 ; i++) {
            System.out.println("2023-0"+i);
//            Integer s = billService.chart("2023-0"+i);
            if (i>9){
                Integer s = billService.chart(year+"-"+i);
                a.add(s);
            }else{
                Integer s = billService.chart(year+"-0"+i);
                a.add(s);
            }
        }
        return a;
    }
    @GetMapping("/account/{year}")
    public List<List> getAccount(@PathVariable(value = "year")String year) {
        List a = new ArrayList();
        for (int i = 1; i <=12 ; i++) {
            System.out.println("2023-0"+i);
//            Integer s = billService.chart("2023-0"+i);
            if (i>9){
                Integer s = accountService.chartAccount(year+"-"+i);
                a.add(s);
            }else{
                Integer s = accountService.chartAccount(year+"-0"+i);
                a.add(s);
            }
        }
        return a;
    }

}
