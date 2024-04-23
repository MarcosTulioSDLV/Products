package com.springboot.products.controllers;

import com.springboot.products.models.InvoiceProduct;
import com.springboot.products.services.InvoiceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class InvoiceProductController {

    private final InvoiceProductService invoiceProductService;

    @Autowired
    public InvoiceProductController(InvoiceProductService invoiceProductService) {
        this.invoiceProductService = invoiceProductService;
    }

    @GetMapping(value = "/invoice-products")
    public ResponseEntity<Page<InvoiceProduct>> getInvoiceProducts(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(invoiceProductService.getInvoiceProducts(pageable));
    }


}
