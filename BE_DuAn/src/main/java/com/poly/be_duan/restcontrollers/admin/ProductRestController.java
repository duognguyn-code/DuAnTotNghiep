package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.entities.*;
import com.poly.be_duan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }
    @PutMapping("{id}")
    public Product update(@PathVariable("id") Integer id,@RequestBody Product product) {
//        product.setId(id);
        return productService.update(product);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }
}
