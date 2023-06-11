package com.poly.be_duan.service;

import com.poly.be_duan.entities.Image;
import com.poly.be_duan.entities.Product;

import java.util.List;

public interface ImageService {
    public List<Image> findAll();

    /**
     * Lấy image theo id
     *
     * @param id
     * @return
     */
    public Image findById(Integer id);

    /**
     * Thêm mới image
     *
     * @param images
     * @return
     */
    public void create(Image images);

    /**
     * Cập nhật image
     *
     * @param images
     * @return
     */
    public Image update(Image images);

    /**
     * Xoá image theo id
     *
     * @param id
     * @return
     */
    public Image delete(Integer id);


    public List<Image> findByProduct(Product product);

    /**
     * Tạo list image
     * @param images
     * @return
     */
    public List<Image> createList(List<Image> images);

}
