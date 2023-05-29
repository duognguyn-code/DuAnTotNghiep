package com.poly.be_duan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart implements Serializable {
    protected static final String PK = "id_carts";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carts", unique = true, nullable = false, precision = 10)
    private int id_carts;

    @Column(name = "status", nullable = false, precision = 10)
    private int status;
    @JsonIgnore
    @OneToMany(mappedBy = "carts")
    private Set<CartDetail> detailCarts;

    
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "username", nullable = false)
    private Account account;

    private boolean equalKeys(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Cart)) {
            return false;
        }
        Cart that = (Cart) other;
        if (this.getId_carts() != that.getId_carts()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getId_carts();
        result = 37 * result + i;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Cart))
            return false;
        return this.equalKeys(other) && ((Cart) other).equalKeys(this);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Carts |");
        sb.append(" id_carts=").append(getId_carts());
        sb.append("]");
        return sb.toString();
    }
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<>(6);
        ret.put("id_carts", Integer.valueOf(getId_carts()));
        return ret;
    }
}
