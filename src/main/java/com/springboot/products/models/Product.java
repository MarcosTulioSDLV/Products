package com.springboot.products.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PRODUCT")
@AllArgsConstructor
@Getter @Setter @ToString(exclude = "invoiceProductList")
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String productNumber;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String productSection;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<InvoiceProduct> invoiceProductList= new ArrayList<>();


    public Product(){
    }

}
