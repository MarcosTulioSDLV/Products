package com.springboot.products.exceptions;

public class InvoiceNotFoundException extends RuntimeException{

    public InvoiceNotFoundException(){
    }

    public InvoiceNotFoundException(String message){
        super(message);
    }

}
