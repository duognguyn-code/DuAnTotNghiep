package com.poly.be_duan.service;

import com.poly.be_duan.entities.Color;
import com.poly.be_duan.entities.Material;

import java.util.List;

public interface MaterialService {
    public List<Material> getAll();

    public Material create(Material material);

    public Material getMaterialByID(Long id);

    public Material update(Material material);

    public void delete(Long id);
}
