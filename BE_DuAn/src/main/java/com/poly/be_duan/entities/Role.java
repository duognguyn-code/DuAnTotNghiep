package com.poly.be_duan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Column(name = "name",nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<Account> accounts;

}
