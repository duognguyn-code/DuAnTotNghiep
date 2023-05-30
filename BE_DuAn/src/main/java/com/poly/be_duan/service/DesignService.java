package com.poly.be_duan.service;

import com.poly.be_duan.entities.Color;
import com.poly.be_duan.entities.Designs;

import java.util.List;

public interface DesignService {
    public List<Designs> getAll();

    public Designs create(Designs designs);

    public Designs getDesignByID(Long id);

    public Designs update(Designs designs);

    public void delete(Long id);
}
