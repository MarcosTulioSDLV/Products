package com.springboot.products.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode
@Embeddable
public class InvoiceProductPK implements Serializable {

    private static final long serialVersionUID=1L;

    private Long invoiceId;

    private Long productId;

    public InvoiceProductPK(){
    }

}
