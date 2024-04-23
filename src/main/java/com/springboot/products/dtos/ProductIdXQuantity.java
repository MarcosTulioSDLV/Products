package com.springboot.products.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter @ToString
public class ProductIdXQuantity {

    private Long productId;

    private Integer quantity;

}
