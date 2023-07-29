package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.entities.Bill_detail;
import com.poly.be_duan.entities.ProductChange;
import com.poly.be_duan.service.BillDetailService;
import com.poly.be_duan.service.BillService;
import com.poly.be_duan.service.ProductChangeService;
import com.poly.be_duan.service.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/rest/staff/productchange")
@CrossOrigin("*")
@RequiredArgsConstructor
public class QLProductChangeRestController {
    private final ProductChangeService productChangeService;

    private final BillDetailService billDetailService;

    private final SendMailService sendMailService;

    @RequestMapping(value = "/comfirmRequest", method = RequestMethod.POST)
    public void confirmRequest(@RequestBody List<Integer> id){
        Bill_detail bill_detail = null;
        for (Integer s: id) {
            ProductChange productChange = productChangeService.findByStatus(s);
            System.out.println(productChange + "id");
            if(s != null && productChange.getStatus() == 1){
                productChange.setStatus(2);
                productChangeService.save(productChange);
                bill_detail = productChange.getBillDetail();
                bill_detail.setStatus(4);
                billDetailService.update(bill_detail, bill_detail.getId());
                System.out.println("gửi mail thành công");
            }else if(s != null && productChange.getStatus() == 2){
                productChange.setStatus(3);
                productChangeService.save(productChange);
                bill_detail = productChange.getBillDetail();
                bill_detail.setStatus(5);
                billDetailService.update(bill_detail, bill_detail.getId());
                System.out.println("gửi mail thành công");
            }else {
                System.out.println("null lỗi");
            }
        }
    }

    @RequestMapping(value = "/cancelRequest", method = RequestMethod.POST)
    public void   cancelRequest(@RequestBody List<Integer>  idProductChange){
        for ( Integer  s :  idProductChange) {
            if(s !=null) {
                ProductChange product = productChangeService.findByStatus(s);
                if(product.getStatus()==3){
                    product.setStatus(6);
                    productChangeService.save(product);
                }else if(product.getStatus()==4){
                    System.out.println("không thể hủy ");
                }
            }
        }
    }
}
