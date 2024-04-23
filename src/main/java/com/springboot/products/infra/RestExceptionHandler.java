package com.springboot.products.infra;

import com.springboot.products.exceptions.*;
import com.springboot.products.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    //---
    //For Product
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProductNotFoundException(ProductNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(ProductNumberExistsException.class)
    public ResponseEntity<String> handleProductNumberExistsException(ProductNumberExistsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT); //return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(ProductNameExistsException.class)
    public ResponseEntity<String> handleProductNameExistsException(ProductNameExistsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }
    //---

    //For Customer
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerFullNameExistsException.class)
    public ResponseEntity<String> handleCustomerFullNameExistsException(CustomerFullNameExistsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerAddressExistsException.class)
    public ResponseEntity<String> handleCustomerAddressExistsException(CustomerAddressExistsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerPhoneNumberExistsException.class)
    public ResponseEntity<String> handleCustomerCustomerPhoneNumberExistsException(CustomerPhoneNumberExistsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }
    //---

    //For Invoice
    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<String> handleInvoiceNotFoundException(InvoiceNotFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughProductsInStockException.class)
    public ResponseEntity<String> handleNotEnoughProductsInStockException(NotEnoughProductsInStockException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    //---




}
