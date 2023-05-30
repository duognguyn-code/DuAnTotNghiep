package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    Color findColorById(Integer id);
}
