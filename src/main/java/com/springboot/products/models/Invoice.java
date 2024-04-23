package com.springboot.products.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_INVOICE")
@AllArgsConstructor
@Getter @Setter @ToString(exclude = "invoiceProductList")
@EqualsAndHashCode
public class Invoice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private Double purchaseTotalPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "invoice",cascade = CascadeType.REMOVE)
    private List<InvoiceProduct> invoiceProductList= new ArrayList<>();


    public Invoice(){
    }


}
