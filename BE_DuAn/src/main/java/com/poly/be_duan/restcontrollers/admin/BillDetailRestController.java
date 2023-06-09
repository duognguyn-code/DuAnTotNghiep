package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.entities.Bill;
import com.poly.be_duan.entities.Bill_detail;
import com.poly.be_duan.service.BillDetailService;
import com.poly.be_duan.service.BillService;
import com.poly.be_duan.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/billDetail")
public class BillDetailRestController {

    @Autowired
    BillDetailService billDetailService;

    @Autowired
    CookieService cookieService;

    @Autowired
    BillService billService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Bill_detail>> getBill_detail(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(billDetailService.getBill_detail(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/getBillByID")
    public List<Bill> getBillByID() {
        int a = Integer.parseInt(cookieService.getValue("idBill",""));
//        System.out.println(a+"id  s");
        return billService.getBill(a);
    }

        @PutMapping("{id}")
        public Bill update(@PathVariable("id") Integer id,@RequestBody Bill bill) {
//        color.setId(id);
//            System.out.println("abcccc");
            return billService.update(bill);
        }

}
