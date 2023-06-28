package com.poly.be_duan.service.impl;

import com.poly.be_duan.entities.Image;
import com.poly.be_duan.entities.Product;
import com.poly.be_duan.repositories.ImageRepository;
import com.poly.be_duan.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;


    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }


    @Override
    public Image create(Image images) {
        return imageRepository.save(images);
    }

    @Override
    public Image update(Image images, Integer id) {
        Optional<Image> optional = findById(id);
        if (optional.isPresent()) {
            return create(images);
        }
        return null;
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
        return imageRepository.saveAll(images);
    }

    @Override
    public Optional<Image> findById(Integer id) {
        return imageRepository.findById(id);
    }
}
