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
    @GetMapping("/admin/product/create")
    public String view222(){
        return "admin/Product/createProduct";
    }
    @GetMapping("/admin/designs")
    public String design(){
        return "admin/Product/Designs";
    }
    @GetMapping("/admin/material")
    public String material(){
        return "admin/Product/material";
    }
    @GetMapping("/admin/color")
    public String color(){
        return "admin/Product/color";
    }
}
