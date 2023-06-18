package com.poly.be_duan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "design")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Designs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_design")
//    private Integer id;
    private Long id;

//    @Column(name = "name", nullable = false)
    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;
}
