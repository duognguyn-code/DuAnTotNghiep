package com.poly.be_duan.service;

import com.poly.be_duan.entities.Size;

import java.util.List;
import java.util.Optional;

public interface SizeService {
    public List<Size> getAll();

    public Size create(Size size);

    public Optional<Size> getSizeByID(Long id);

    public Size update(Size size);

    public void delete(Long id);
}
