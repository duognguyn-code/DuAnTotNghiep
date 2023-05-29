package com.poly.be_duan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.be_duan.validations.products.UniqueProductName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TermVector;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.search.annotations.Field;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "products")
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product implements Serializable {
    protected static final String PK = "productId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_id", unique = true, nullable = false, precision = 10)
    private int productId;

    @NotBlank(message = "{Product.productName.NotBlank}")
    @UniqueProductName(message = "{Product.productName.UniqueProductName}")
    @Length(min = 5, max = 100,message = "{Product.productName.Length}")
    @Field(termVector = TermVector.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "Product_name", nullable = false, length = 100)
    private String productName;

    @NotNull(message = "{Product.status.NotNull}")
    @Min(value = 0)
    @Max(value = 1)
    @Column(name = "Status", nullable = false, precision = 10)
    private int status;

    @JsonIgnore
    @OneToMany(mappedBy = "products")
    private Set<ProductVariant> productVariants;

    @JsonIgnore
    @OneToMany(mappedBy = "products")
    private Set<ProductOption> productOptions;

}
