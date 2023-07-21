package com.poly.be_duan.restcontrollers.admin;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.be_duan.entities.Bill;
import com.poly.be_duan.entities.Bill_detail;
import com.poly.be_duan.service.BillDetailService;
import com.poly.be_duan.service.BillService;
import com.poly.be_duan.service.CookieService;
import com.poly.be_duan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bill")
public class BillRestController {
    @Autowired
    BillService billService;

    @Autowired
    BillDetailService billDetailService;

    @Autowired
    CookieService cookieService;

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Bill>> getAll() {
        System.out.println(billService.getAll());
        try {
            return ResponseEntity.ok(billService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

    @GetMapping("/{id}")
    public List<Bill> getByID(@PathVariable(value = "id") Integer id) {

        return billService.getBill(id);

    }

    @GetMapping("/{phone}/{sts}/date")
    public ResponseEntity<List<Bill>> searchBill(@RequestParam("date1") String date1, @RequestParam("date2") String date2, @PathVariable(value = "phone") String phone, @PathVariable(value = "sts") String sts) throws ParseException,Exception {


        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        if (date1.equals("null")) {
            date1 = "2023/05/03";
        }
        if (date2.equals("null")) {
            date2 = today.format(dateTimeFormatter);
        }
            if (phone.equals(" ")) {
                phone = "0";
            }
            Date dates1 = new Date(date1);
            Date dates2 = new Date(date2);
            if (sts == null | sts.equals("6")) {
                return ResponseEntity.ok(billService.searchByPhoneAndDate(phone, dates1, dates2));
            } else {
                int st = Integer.parseInt(sts);
                return ResponseEntity.ok(billService.searchByPhoneAndDateAndStatus(phone, dates1, dates2, st));
            }

        }

    @PutMapping("/updateStatus/{id}")
    public Bill updateStatus(@PathVariable(value = "id")Integer id,@RequestBody Bill bill) {
        List<Bill_detail> detailStatus = billDetailService.getBill_detailForMoney(id);
        if (detailStatus.isEmpty()){
            bill.setStatus(5);
            return billService.update(bill);
        }
        else {
            Bill billOld = billService.findBillByID(bill.getId()).get();
            if (bill.getStatus() < billOld.getStatus()) {
                return null;
            } else {
                billOld.setStatus(bill.getStatus());
                return billService.updateStatus(billOld);
            }
        }

    }
    @PostMapping()
    public Bill create(@RequestBody JsonNode billData) {
        return billService.create(billData);
    }

    @PutMapping("/updateBill")
    public Bill update(@RequestBody Bill bill) {
//        color.setId(id);
        return billService.update(bill);
    }

    @PutMapping("/updateTotalMoney/{money}/{id}")
    public Bill updateTotalMoney(@PathVariable(value = "money")Integer money,@PathVariable(value = "id")Integer id) {
        BigDecimal mn = new BigDecimal(money);
        Bill bill = billService.findBillByID(id).get();
        bill.setTotalMoney(mn);
        return billService.update(bill);
    }
}
