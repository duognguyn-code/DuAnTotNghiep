package com.poly.be_duan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "color")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colors")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;
}
