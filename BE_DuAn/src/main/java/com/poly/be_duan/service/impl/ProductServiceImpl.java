package com.poly.be_duan.service.impl;

import com.poly.be_duan.dto.ProductDetailDTO;
import com.poly.be_duan.dto.ProductResponDTO;
import com.poly.be_duan.entities.*;
import com.poly.be_duan.repositories.*;
import com.poly.be_duan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Autowired
    private DesignRepository designRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productDetailRepository1) {
        this.productRepository = productDetailRepository1;
    }

    public List<Product> getByColor(String color, String design, String material, String size) {
        return (List<Product>) productRepository.getByColor(color, design, material, size);
    }


    @Override
    public Page<Product> getAll(Pageable page) {
        return productRepository.findAll(page);
    }

    @Override
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public List<Product> save(List<Product> entities) {
        return (List<Product>) productRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {

        productRepository.deleteById(id);
    }


    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        Page<Product> entityPage = productRepository.findAll(pageable);
        List<Product> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Product update(Product entity, Integer id) {
        Optional<Product> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public List<Product> search(String name, String color, String material, String size, String design, BigDecimal min, BigDecimal max, Integer status) {
        return productRepository.search(name, color, material, size, design, min, max, status);
    }

    @Override
    public BigDecimal searchPriceMin() {
        return productRepository.searchMin();
    }

    @Override
    public BigDecimal searchPriceMAX() {
        return productRepository.searchMax();
    }

    @Override
    public ProductDetailDTO getDetailProduct(Integer id) {
        List<Color> listColor = new ArrayList<>();
        List<Material> listMaterial = new ArrayList<>();
        List<Size> listSize = new ArrayList<>();
        List<Designs> listDesign = new ArrayList<>();
        List<Integer> listImage = new ArrayList<>();
        List<String> listimage = new ArrayList<>();
        for (int x : productRepository.getlistDetailProductColor(id)
        ) {
            listColor.add(colorRepository.findById(x).get());
        }
        for (int x : productRepository.getlistDetailProductDesign(id)
        ) {
            listDesign.add(designRepository.findById(x).get());
        }
        for (int x : productRepository.getlistDetailProductMaterial(id)
        ) {
            listMaterial.add(materialRepository.findById(x).get());
        }
        for (int x : productRepository.getlistDetailProductSize(id)
        ) {
            listSize.add(sizeRepository.findById(x).get());
        }
        for (int x : productRepository.getIdimage(id)
        ) {
            listImage.add(x);
        }
        if (listImage.size() != 0) {
            for (int i = 0; i < listImage.size(); i++) {
                if (productRepository.getImg(listImage.get(i)) != null) {
                    listimage.add(productRepository.getImg(listImage.get(i)));
                }
            }
        }
        ProductDetailDTO detailproduct = new ProductDetailDTO();
        detailproduct.setId(id);
        detailproduct.setName(categoryRepository.findById(id).get().getName());
        detailproduct.setColors(listColor);
        detailproduct.setDesigns(listDesign);
        detailproduct.setMaterials(listMaterial);
        detailproduct.setSizes(listSize);
        detailproduct.setImages(listimage);
        detailproduct.setPriceMin(getMaxMinPriceProduct(id).get(0));
        detailproduct.setPriceMax(getMaxMinPriceProduct(id).get(1));
        return detailproduct;
    }
    private List<BigDecimal> getMaxMinPriceProduct(Integer id){
        List<BigDecimal> listPriceMinMax = new ArrayList<>();
        listPriceMinMax.add(productRepository.getMinPrice(id));
        listPriceMinMax.add(productRepository.getMaxPrice(id));
        return listPriceMinMax;
    }
    public List<ProductResponDTO> findByCategoryAndStatus(Integer id){
        List<Product>   products = productRepository.getProductByCategoryIdAndStatus(id, 1);
        System.out.println(products.size());
        List<ProductResponDTO> productResponDTOList = new ArrayList<>();
        for (int i =0; i < products.size(); i++){
            ProductResponDTO productResponDTO = new ProductResponDTO();
            System.out.println(i);
            Product productImage = productRepository.findById(products.get(i).getId()).orElse(null);
            productResponDTO.setIdProduct((products.get(i).getId()));
            productResponDTO.setName(products.get(i).getName());
            productResponDTO.setCategory(products.get(i).getCategory());
            productResponDTO.setPrice(products.get(i).getPrice());
            productResponDTO.setMaterial(products.get(i).getMaterial());
            productResponDTO.setColor(products.get(i).getColor());
            productResponDTO.setDesigns(products.get(i).getDesign());
            productResponDTO.setSize(products.get(i).getSize());

        }
        return productResponDTOList;
    }
    private List<ProductDetailDTO> getallProduct(){
        List<ProductDetailDTO> listgetAllProduct= new ArrayList<>();
        for (int x: productRepository.getlistDetailProductCategory()
        ) {
            listgetAllProduct.add(getDetailProduct(x));
        }
        return listgetAllProduct;
    }
}
