package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.entities.Material;
import com.poly.be_duan.entities.Size;
import com.poly.be_duan.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/size")
public class SizeRestController {

    @Autowired
    private SizeService sizeService;

    @GetMapping()
    public List<Size> getAll(){
        return sizeService.getAll();
    }

    @PostMapping
    public Size create(@RequestBody Size size) {
        return sizeService.create(size);
    }

    @PutMapping("{id}")
    public Size update(@PathVariable("id") Long id, Size size) {
        size.setId(id);
        return sizeService.update(size);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        sizeService.delete(id);
    }
}
