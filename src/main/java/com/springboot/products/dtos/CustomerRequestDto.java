package com.springboot.products.dtos;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDto(@NotBlank String fullName,
                                 @NotBlank String address,
                                 @NotBlank String phoneNumber,
                                 @NotBlank String city) {

}

