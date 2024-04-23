package com.springboot.products.exceptions;

public class ProductNameExistsException extends RuntimeException{

    public ProductNameExistsException(){
    }

    public ProductNameExistsException(String message){
        super(message);
    }

}
