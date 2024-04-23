package com.springboot.products.exceptions;

public class ProductNumberExistsException extends RuntimeException{

    public ProductNumberExistsException(){
    }

    public ProductNumberExistsException(String message){
        super(message);
    }
}
