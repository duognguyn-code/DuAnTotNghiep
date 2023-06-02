package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.entities.Product;
import com.poly.be_duan.service.ProductService;
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

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        try {
            return ResponseEntity.ok(productService.getAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @PostMapping
    public Product create(Product product) {
        return productService.create(product);
    }
    @PutMapping("{id}")
    public Product update(@PathVariable("id") Integer id, Product product) {
        product.setId(id);
        return productService.update(product);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }
}
