package com.springboot.products.services;

import com.springboot.products.models.InvoiceProduct;
import com.springboot.products.repositories.InvoiceProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;


    @Autowired
    public InvoiceProductService(InvoiceProductRepository invoiceProductRepository) {
        this.invoiceProductRepository = invoiceProductRepository;
    }


    public Page<InvoiceProduct> getInvoiceProducts(Pageable pageable) {
        return invoiceProductRepository.findAll(pageable);
    }


}
