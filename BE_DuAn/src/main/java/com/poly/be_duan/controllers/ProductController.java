package com.poly.be_duan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/admin/productdetail")
    public String view(){
        return "admin/ProductDetail/TabProductDetail";
    }
    @GetMapping("/admin/product")
    public String view22(){
        return "admin/Product/TabProduct";
    }
    @GetMapping("/admin/designs")
    public String view122(){
        return "admin/Product/Designs";
    }
    @GetMapping("/admin/material")
    public String view121(){
        return "admin/Product/material";
    }
}
