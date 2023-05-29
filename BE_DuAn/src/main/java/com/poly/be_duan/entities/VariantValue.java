package com.poly.be_duan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "variant_values")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VariantValue implements Serializable {
    @EmbeddedId
    protected VariantValuesPK variantValuesPK;

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    @Column(name = "Status", nullable = false, precision = 10)
    private int status;
    @ManyToOne(optional = false)
    @JoinColumn(name = "Value_id", nullable = false)
    private OptionValue optionValues;

    @ManyToOne(optional = false, cascade = javax.persistence.CascadeType.MERGE)
//	@Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumns({ @JoinColumn(name = "Option_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "Product_id", nullable = false, insertable = false, updatable = false) })
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private ProductOption productOptions;
//	private ProductOption productOptions2;
//	@ManyToOne(optional = false)

    @ManyToOne(optional = false, cascade = javax.persistence.CascadeType.ALL)
//	@Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "Variant_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private ProductVariant productVariants;
}
