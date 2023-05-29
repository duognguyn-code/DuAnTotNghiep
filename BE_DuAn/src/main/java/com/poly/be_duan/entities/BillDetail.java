package com.poly.be_duan.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name = "bill_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BillDetail implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id_bill_detail", unique = true, nullable = false, length = 50)
    private String id_bill_detail;

    @Min(value = 0)
    @NotNull
    @Column(name = "quantity", nullable = false, precision = 10)
    private int quantity;

    @Min(value = 0)
    @Column(name = "price", nullable = false, precision = 10)
    private BigDecimal price;


    @Column(name = "date_return", nullable = false)
    private LocalDateTime date_return;

    @Column(name = "money_refund", nullable = false, precision = 10)
    private BigDecimal money_refund;

    @Column(name = "description", nullable = false, precision = 255)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_bills", nullable = false)
    private Bill id_bills;

    @Column(name = "status", nullable = false, precision = 10)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_bill_detail_old", nullable = true)
    private BillDetail id_bill_detail_old;


    private boolean equalKeys(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BillDetail)) {
            return false;
        }
        BillDetail that = (BillDetail) other;
        if (this.getId_bill_detail() != that.getId_bill_detail()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        long i;
        long result = 17;
        i = getId_bill_detail().hashCode();
        result = 37 * result + i;
        return (int) result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BillDetail))
            return false;
        return this.equalKeys(other) && ((BillDetail) other).equalKeys(this);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[DetailBills |");
        sb.append(" id_bill_detail=").append(getId_bill_detail());
        sb.append("]");
        return sb.toString();
    }
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<>(6);
        ret.put("id_bill_detail", getId_bill_detail());
        return ret;
    }
}
