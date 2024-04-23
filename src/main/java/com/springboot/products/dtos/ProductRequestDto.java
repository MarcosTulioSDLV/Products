package com.springboot.products.dtos;

import jakarta.validation.constraints.*;


public record ProductRequestDto(@NotBlank String productNumber,
                                @NotBlank @Size(max = 40) String name,
                                @NotNull @DecimalMin("0") Double price,
                                @NotNull @PositiveOrZero Integer stockQuantity,
                                @NotBlank String country,
                                @NotBlank String productSection){

}
