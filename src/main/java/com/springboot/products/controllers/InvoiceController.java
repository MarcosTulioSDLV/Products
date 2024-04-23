package com.springboot.products.controllers;

import com.springboot.products.dtos.AddInvoiceRequest;
import com.springboot.products.dtos.AddInvoiceRequestDto;
import com.springboot.products.dtos.InvoiceRequestDto;
import com.springboot.products.models.Invoice;
import com.springboot.products.services.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping(value = "/api")
public class InvoiceController {

    private final InvoiceService invoiceService;


    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(value = "/invoices")
    public ResponseEntity<Page<Invoice>> getInvoices(@PageableDefault(size = 5) Pageable pageable){
        Page<Invoice> invoicesPage= invoiceService.getInvoices(pageable);
        return ResponseEntity.ok(invoicesPage);
    }

    @GetMapping(value = "/invoices/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id){
        Invoice invoice= invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }


    @PostMapping(value = "/invoices/{customerId}")
    public ResponseEntity<Invoice> addInvoiceByProductIdsAndQuantities(@PathVariable Long customerId,
                                                                       @RequestBody @Valid AddInvoiceRequestDto addInvoiceRequestDto){
        var addInvoiceRequest= new AddInvoiceRequest(addInvoiceRequestDto.invoiceRequestDto(),addInvoiceRequestDto.productIdXQuantityRequestDtoList());

        Invoice invoice= addInvoiceRequest.getInvoice();
        invoice.setPaymentDate(LocalDateTime.now(ZoneId.of("UTC-5")));//Local time of Colombia

        var productIdXQuantityList= addInvoiceRequest.getProductXQuantityList();

        Invoice addedInvoice= invoiceService.addInvoice(customerId,invoice,productIdXQuantityList);
        return new ResponseEntity<>(addedInvoice,HttpStatus.CREATED);
    }


    @PutMapping(value = "/invoices/{id}")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody @Valid InvoiceRequestDto invoiceRequestDto,
                                                 @PathVariable Long id){
        Invoice invoice= new Invoice();
        BeanUtils.copyProperties(invoiceRequestDto,invoice);
        invoice.setId(id);

        Invoice updatedInvoice= invoiceService.updateInvoice(invoice);
        return ResponseEntity.ok(updatedInvoice);
    }


    /*@DeleteMapping(value = "/invoices/{id}")
    public ResponseEntity<Invoice> removeInvoice(@PathVariable Long id){
        Invoice removedInvoice= invoiceService.removeInvoice(id);
        return ResponseEntity.ok(removedInvoice);
    }*/

}
