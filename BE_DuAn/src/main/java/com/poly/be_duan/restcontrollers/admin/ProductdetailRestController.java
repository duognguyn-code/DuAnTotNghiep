package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.beans.SaveProductRequest;
import com.poly.be_duan.entities.*;
import com.poly.be_duan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/productDetail")
public class ProductdetailRestController {
    @Autowired
    private ProductDetailService productService;

    @Autowired
    private SizeService sizeService;


    @Autowired
    private MaterialService materialService;

    @Autowired
    private DesignService designService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private ImageService imageService;

    @GetMapping("")
    public ResponseEntity<List<Product_detail>> getAll() {
        try {
            return ResponseEntity.ok(productService.getAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/getAllSize")
    public ResponseEntity<List<Size>> getAllSize() {
        try {
            return ResponseEntity.ok(sizeService.getAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/getAllMaterial")
    public ResponseEntity<List<Material>> getAllMaterial() {
        try {
            return ResponseEntity.ok(materialService.getAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @GetMapping("/getAllColor")
    public ResponseEntity<List<Color>> getAllColor() {
        try {
            return ResponseEntity.ok(colorService.getAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/getAllDesign")
    public ResponseEntity<List<Designs>> getAllDesign() {
        try {
            return ResponseEntity.ok(designService.getAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    private String generationName(SaveProductRequest prd){
        String name="";
        List<Material> listMate =materialService.getAll();
        List<Designs> Designs = designService.getAll();
        List<Color> listColor = colorService.getAll();
        List<Size> listSize = sizeService.getAll();
        for (Material cate:listMate
        ) {
            if(prd.getMaterial().getId()==cate.getId()){
                name+= cate.getName();
            }
        }
        for (Designs des:Designs
        ) {
            if(prd.getDesign().getId()==des.getId()){
                name+=des.getName();
            }
        }
        for (Color color:listColor
        ) {
            if(prd.getColor().getId()==color.getId()){
                name+=" Màu " + color.getName();
            }
        }
        for (Size size:listSize
        ) {
            if(prd.getSize().getId() == size.getId()){
                name+=" Size " +size.getName();
            }
        }
        return name;
    }

    @RequestMapping(path = "/saveProduct", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public void save(@ModelAttribute SaveProductRequest saveProductRequest){
        Product_detail pd = new Product_detail();
        pd.setColor(saveProductRequest.getColor());
        pd.setMaterial(saveProductRequest.getMaterial());
        pd.setDesign(saveProductRequest.getDesign());
        pd.setSize(saveProductRequest.getSize());
        pd.setProduct(saveProductRequest.getProduct());

        productService.create(pd);

    }
}
