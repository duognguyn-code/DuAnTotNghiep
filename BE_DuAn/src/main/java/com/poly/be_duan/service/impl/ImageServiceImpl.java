package com.poly.be_duan.service.impl;

import com.poly.be_duan.entities.Image;
import com.poly.be_duan.entities.Product;
import com.poly.be_duan.repositories.ImageRepository;
import com.poly.be_duan.repositories.ProductRepository;
import com.poly.be_duan.service.ImageService;
import com.poly.be_duan.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;


    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(Integer id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public void create(Image images) {
        try {
            MultipartFile file = images.getFile();
            String fileName = file.getOriginalFilename();
            String filePath = "/path/to/image/directory/" + fileName; // Đường dẫn tới thư mục lưu trữ ảnh

            // Lưu trữ ảnh vào thư mục
            File destination = new File(filePath);
            file.transferTo(destination);

            // Lưu thông tin đường dẫn ảnh vào đối tượng Image
            images.setUrlimage(filePath);
        } catch (IOException e) {
            // Xử lý lỗi khi lưu trữ ảnh
            e.printStackTrace();
        }
    }

    @Override
    public Image update(Image images) {
        return imageRepository.save(images);
    }

    @Override
    public Image delete(Integer id) {
        Image image =imageRepository.findById(id).get();
        imageRepository.delete(image);
        return image;
    }

    @Override
    public List<Image> findByProduct(Product product) {

        return imageRepository.findByProduct(product);
    }

    @Override
    public List<Image> createList(List<Image> images) {
        return null;
    }
}
