package com.poly.be_duan.restcontrollers.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.poly.be_duan.dto.ChangeProductDetailDTO;
import com.poly.be_duan.dto.ProductChangeDTO;
import com.poly.be_duan.entities.Bill_detail;
import com.poly.be_duan.entities.ChangeProductDetail;
import com.poly.be_duan.entities.Image;
import com.poly.be_duan.entities.ProductChange;
import com.poly.be_duan.repositories.BillDetailRepository;
import com.poly.be_duan.repositories.BillRepository;
import com.poly.be_duan.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping(value= "/rest/user/productchange")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductChangeRestController {
    private final ProductChangeService productChangeService;
    private final ChangeProductDetailService changeProductDetailService;
    private final Cloudinary cloud;
    private final ImageService imageService;
    private final AccountService accountService;
    private final BillDetailService billDetailService;
    private final BillDetailRepository billDetailRepository;
    private final BillRepository billRepository;

    @RequestMapping(value = "/findProductChange/{id}",method = RequestMethod.GET)
    public Bill_detail findByProductChange (@PathVariable("id") Integer id){
        Optional<Bill_detail> orderDetails =  billDetailRepository.findById(id);
        return orderDetails.get();
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public void requestProductChange(@ModelAttribute ProductChangeDTO productChangeDTO){
        try {
            if(productChangeDTO != null){
                ProductChange p =  new ProductChange();
                p.setAccount(productChangeDTO.getAccount());
                p.setDateChange(new Date());
                p.setDescription(productChangeDTO.getDescription());
                p.setEmail(productChangeDTO.getEmail());
                p.setQuantityProductChange(productChangeDTO.getQuantityProductChange());
                p.setBillDetail(productChangeDTO.getBill_detail());
                p.setStatus(1);
                productChangeService.save(p);
                Bill_detail bill_detail = billDetailRepository.findById(productChangeDTO.getBill_detail().getId()).get();
                bill_detail.setStatus(3);
                billDetailService.update(bill_detail, bill_detail.getId());
                for (MultipartFile multipartFile: productChangeDTO.getFiles()) {
                    Map r = this.cloud.uploader().upload(multipartFile.getBytes(),
                            ObjectUtils.asMap(
                                    "cloud_name", "dcll6yp9s",
                                    "api_key", "916219768485447",
                                    "api_secret", "zUlI7pdWryWsQ66Lrc7yCZW0Xxg",
                                    "secure", true,
                                    "folders","c202a2cae1893315d8bccb24fd1e34b816"
                            ));
                    Image image = new Image();
                    image.setUrlimage(r.get("secure_url").toString());
                    image.setProductChange(p);
                    imageService.create(image);
                }
            }else System.out.println("null");
        }catch (Exception e){
            e.getMessage();
        }
    }

    @RequestMapping(path = "/saveRequest",method = RequestMethod.POST,
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public void saveRequest(@ModelAttribute ChangeProductDetailDTO changeProductDetailDTO){
        try {
            if(changeProductDetailDTO != null){
                ChangeProductDetail changeProductDetail = new ChangeProductDetail();
                changeProductDetail.setBillDetail(changeProductDetailDTO.getBill_detail());
                changeProductDetailService.createChangeDetails(changeProductDetailDTO.getBill_detail().getId());
                Optional<Bill_detail> bill_detail = billDetailService.findById(changeProductDetailDTO.getBill_detail().getId());
                bill_detail.orElseThrow().setStatus(1);
                System.out.println(bill_detail.get().getDateReturn());
                billDetailService.save(bill_detail.get());
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    @RequestMapping(value= "/getAll", method = RequestMethod.GET)
    public List<ProductChange> listProductchange(){
        List<ProductChange> productChangeList = productChangeService.listProductChange();
        if(productChangeList.isEmpty()){
            return  null;
        }
        Comparator<ProductChange> comparator = new Comparator<ProductChange>() {
            @Override
            public int compare(ProductChange o1, ProductChange o2) {
                return o2.getId().compareTo(o1.getId());
            }
        };
        Collections.sort(productChangeList, comparator);
        return productChangeList;
    }

    @RequestMapping(value= "/getPrChangeDetails/{idChange}",  method =  RequestMethod.GET )
    public ChangeProductDetail listPrChangeDetails(@PathVariable("idChange") ProductChange  idChange) {
        ChangeProductDetail listPrChangeDetails = changeProductDetailService.findPrChangeDetails(idChange);
        if(listPrChangeDetails == null){
            return null;
        }
        return listPrChangeDetails;
    }

    @RequestMapping(value= "/getPrChangeByUser/{username}", method =  RequestMethod.GET)
    public List<ProductChange> findByUser(@RequestParam("username") String user) {
        List<ProductChange> listPrChangeDetails = productChangeService.findByUsername("Dương");
        return listPrChangeDetails;
    }

    @GetMapping(value = "/findImageByPr/{id}")
    public List<Image>   findAllImageByPr(@PathVariable("id") Integer id){
        List<Image> listImage = imageService.findImageByPr(id);
        if(listImage !=null){
            return listImage;
        }
        return null;
    }
}
