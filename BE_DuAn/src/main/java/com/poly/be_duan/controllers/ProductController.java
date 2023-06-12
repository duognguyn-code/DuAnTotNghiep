package com.poly.be_duan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/admin/productdetail")
    public String viewPD(){
        return "admin/ProductDetail/List_ProductDetail";
    }
    @GetMapping("/admin/addproductdetail")
    public String addPD(){
        return "admin/ProductDetail/Product_detail";
    }
    @GetMapping("/admin/product")
    public String viewProduct(){
        return "admin/Product/listProduct";
    }
    @GetMapping("/admin/addproduct")
    public String addProduct(){
        return "admin/Product/addproduct";
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
