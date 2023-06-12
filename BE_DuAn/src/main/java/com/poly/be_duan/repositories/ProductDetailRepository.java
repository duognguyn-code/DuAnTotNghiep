package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Product_detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<Product_detail, Integer> {
//    @Query("SELECT color.name,designs.name,material.name,image.url_image,size.name,product_detail.status,products.name\n" +
//            "FROM product_detail\n" +
//            "INNER JOIN color\n" +
//            "ON product_detail.id_color = color.id_colors\n" +
//            "INNER JOIN material\n" +
//            "ON product_detail.id_material = material.id_materials\n" +
//            "INNER JOIN designs\n" +
//            "ON product_detail.id_design = designs.id_designs\n" +
//            "INNER JOIN products\n" +
//            "ON product_detail.id_product = products.id_products\n" +
//            "INNER JOIN image\n" +
//            "ON product_detail.id_image = image.id_image\n" +
//            "INNER JOIN size\n" +
//            "ON product_detail.id_size = size.id_size")
//    public List<Product_detail> fillAll1( );

@Query("SELECT p FROM Product_detail p WHERE  (p.color.name LIKE %?1% " +
        "or p.design.name LIKE %?2% o" +
        "r p.material.name LIKE %?3% " +
        "or p.size.name LIKE %?4% " +
        "or p.product.name LIKE %?5% )")
//public List<Product> search(String keyword);
    public List<Product_detail> getByColor(String color, String design,String material, String size,String product);

}
