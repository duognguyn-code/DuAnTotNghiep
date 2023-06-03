package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.entities.Designs;
import com.poly.be_duan.entities.Material;
import com.poly.be_duan.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/design")
public class DesignRestController {

    @Autowired
    private DesignService designService;
    @GetMapping()
    public List<Designs> getAll() {
        return designService.getAll();
    }

    @PostMapping
    public Designs create(Designs designs) {
        return designService.create(designs);
    }

    @PutMapping("{id}")
    public Designs update(@PathVariable("id") Long id, Designs designs) {
        designs.setId_designs(id);
        return designService.update(designs);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        designService.delete(id);
    }
}
