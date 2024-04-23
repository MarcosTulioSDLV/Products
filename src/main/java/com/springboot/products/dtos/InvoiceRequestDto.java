package com.springboot.products.dtos;

import jakarta.validation.constraints.NotBlank;

public record InvoiceRequestDto(@NotBlank String paymentMethod) {

}
