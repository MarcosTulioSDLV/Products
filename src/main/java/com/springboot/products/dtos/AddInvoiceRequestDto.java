package com.springboot.products.dtos;

import com.springboot.products.models.Invoice;
import jakarta.validation.Valid;

import java.util.List;

public record AddInvoiceRequestDto(@Valid InvoiceRequestDto invoiceRequestDto,
                                   @Valid List<ProductIdXQuantityRequestDto> productIdXQuantityRequestDtoList) {

}
