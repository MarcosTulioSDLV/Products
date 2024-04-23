package com.springboot.products.exceptions;

public class CustomerAddressExistsException extends RuntimeException{

    public CustomerAddressExistsException(){
    }

    public CustomerAddressExistsException(String message){
        super(message);
    }
}
