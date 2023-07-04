package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.dto.ProductDetailDTO;
import com.poly.be_duan.dto.ProductResponDTO;
import com.poly.be_duan.entities.Category;
import com.poly.be_duan.entities.Product;
import com.poly.be_duan.service.CategoryService;
import com.poly.be_duan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/rest/guest")
public class GuestRestController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping("/product/cate-product/{id}")
    public List<ProductResponDTO> findByCateProductId(@PathVariable("id") Integer id) throws Exception{
        Optional<Category> cate = categoryService.findById(id);
        if(cate.isEmpty()){
            return null;
        }
        List<ProductResponDTO> productResponDTOList = productService.findByCategoryAndStatus(cate.get().getIdCategory());
        if (productResponDTOList == null) throw new Exception("không tìm thấy sản phẩm");
        return productResponDTOList;
    }
    @RequestMapping("/product/product_detail/{idcate}")
    public ProductDetailDTO getDetailProduct(@PathVariable("idcate") Integer id){
        return productService.getDetailProduct(id);
    }
    @RequestMapping("/product/get_detail_product/{idDesign}/{idSize}/{idColor}/{idMaterial}")
    public Product getdetailProduct(
            @PathVariable("idDesign")Integer idDesign,
            @PathVariable("idSize")Integer idSize,
            @PathVariable("idColor")Integer idColor,
            @PathVariable("idMaterial")Integer idMaterial
    ){
        return productService.getdeTailPrd(idDesign,idSize,idColor,idMaterial);
    }
}
