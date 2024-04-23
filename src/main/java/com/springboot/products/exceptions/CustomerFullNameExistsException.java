package com.springboot.products.exceptions;


public class CustomerFullNameExistsException extends RuntimeException {

    public CustomerFullNameExistsException(){
    }

    public CustomerFullNameExistsException(String message){
        super(message);
    }

}
