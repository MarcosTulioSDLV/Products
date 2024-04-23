package com.springboot.products.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_INVOICE_PRODUCT")
@AllArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode
public class InvoiceProduct {

    @EmbeddedId
    private InvoiceProductPK invoiceProductPK;

    @ManyToOne
    @JoinColumn(name = "invoice_id",insertable = false,updatable = false)
    @MapsId("invoiceId")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id",insertable = false,updatable = false)
    @MapsId("productId")
    private Product product;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private Double productTotalPrice;


    public InvoiceProduct(){
    }

}
