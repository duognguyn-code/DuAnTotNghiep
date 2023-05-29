package com.poly.be_duan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "sale")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sale implements Serializable {
    protected static final String PK = "id_sales";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id_sales", unique = true, nullable = false, length = 36)
    private String id_sales;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "created_date", nullable = true)
    private LocalDateTime created_date;

    @Column(name = "start_date", nullable = true)
    private LocalDateTime start_date;

    @Column(name = "end_date", nullable = true)
    private LocalDateTime end_date;

    @Column(name = "percent_sale")
    private Integer percent_sale;

    @Column(name = "money_sale")
    private BigDecimal money_sale;

    @Column(name = "value_min")
    private BigDecimal valueMin;

    @Column(name = "value_max")
    private BigDecimal value_max;

    @Column(name = "type_sale")
    private Integer type_sale;

    @Column(name = "description", nullable = false)
    private String description;


    @Min(value = 0)
    @Column(name = "status", precision = 10)
    private int status;

    private boolean equalKeys(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Sale)) {
            return false;
        }
        Sale that = (Sale) other;
        Object mySaleId = this.getId_sales();
        Object yourSaleId = that.getId_sales();
        if (mySaleId == null ? yourSaleId != null : !mySaleId.equals(yourSaleId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Sale.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Sale))
            return false;
        return this.equalKeys(other) && ((Sale) other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        if (getId_sales() == null) {
            i = 0;
        } else {
            i = getId_sales().hashCode();
        }
        result = 37 * result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Sale |");
        sb.append(" id_sales=").append(getId_sales());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<>(6);
        ret.put("id_sales", getId_sales());
        return ret;
    }
}
