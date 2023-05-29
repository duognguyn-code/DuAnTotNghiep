package com.poly.be_duan.service.impl;

import com.poly.be_duan.beans.CustomerBean;
import com.poly.be_duan.entities.User;
import com.poly.be_duan.repositories.UserRepository;
import com.poly.be_duan.service.UserService;
import com.poly.be_duan.utils.Constant;
import com.poly.be_duan.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public synchronized List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public synchronized User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public synchronized User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findApproximatePhone(String phone) {
        return userRepository.findApproximatePhone(phone);
    }

    @Override
    public synchronized User delete(User user) {
        userRepository.delete(user);
        return user;
    }

    @Override
    public synchronized User findByEmail(String email) {
        return userRepository.findByEmailLike(email);
    }

    @Override
    public synchronized User findByPhone(String phone) {
        return userRepository.findByPhoneLike(phone);
    }

    @Override
    public Optional<User> checkEmailUpdate(String email, int id) {
        return Optional.empty();
    }

    @Override
    public synchronized Optional<User> checkPhoneUpdate(String phone, int id) {
        return null;
    }

    @Override
    public synchronized Optional<User> checkEmail(String email) {
        return userRepository.checkEmail(email);
    }

    @Override
    public List<User> findApproximatePhoneorEmail(String input) {
        return userRepository.findApproximatePhoneorEmail(input);
    }

    @Override
    public synchronized List<User> findUserSalse() {
        return userRepository.findUserSalse();
    }

    @Override
    public synchronized List<User> findByStatus(Integer status) {
        return userRepository.findByStatusLike(status);
    }

    @Override
    public synchronized User createCustomer(CustomerBean customerBean) {
        User user = new User();
        user.setFullName(customerBean.getFullname());
        user.setEmail(customerBean.getEmail());
        user.setPhone(customerBean.getPhone());
        user.setCreatedDate(LocalDateTime.now());
        user.setSex(customerBean.getSex());
        user.setRole(Constant.ROLE_CUSTOMER_USER);
        user.setStatus(Constant.STATUS_ACTIVE_USER);
        user.setPassword(EncryptUtil.encrypt(Constant.PASSWORD_DEFAULT_USER));
        userRepository.save(user);
        return user;
    }
}
