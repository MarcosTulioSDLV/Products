package com.springboot.products.exceptions;

public class CustomerPhoneNumberExistsException extends RuntimeException{

    public CustomerPhoneNumberExistsException(){
    }

    public CustomerPhoneNumberExistsException(String message){
        super(message);
    }

}
