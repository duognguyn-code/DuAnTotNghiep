package com.poly.be_duan.service.impl;

import com.poly.be_duan.entities.Role;
import com.poly.be_duan.repositories.RoleRepository;
import com.poly.be_duan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public Role getRoleByID(Long id) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
