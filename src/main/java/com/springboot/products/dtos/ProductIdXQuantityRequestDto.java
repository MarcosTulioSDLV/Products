package com.springboot.products.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ProductIdXQuantityRequestDto(@NotNull @Positive Long productId,
                                           @NotNull @Positive Integer quantity) {

}
