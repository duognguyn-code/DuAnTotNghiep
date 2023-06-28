package com.poly.be_duan.service.impl;

import com.poly.be_duan.entities.Cart;
import com.poly.be_duan.repositories.CartRepository;
import com.poly.be_duan.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }
}
