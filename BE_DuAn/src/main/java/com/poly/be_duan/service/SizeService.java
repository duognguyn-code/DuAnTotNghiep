package com.poly.be_duan.service;

import com.poly.be_duan.entities.Designs;
import com.poly.be_duan.entities.Size;

import java.util.List;

public interface SizeService {
    public List<Size> getAll();

    public Size create(Size size);

    public Size getSizeByID(Long id);

    public Size update(Size size);

    public void delete(Long id);
}
