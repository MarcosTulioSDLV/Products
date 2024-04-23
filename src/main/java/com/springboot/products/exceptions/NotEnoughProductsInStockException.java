package com.springboot.products.exceptions;

public class NotEnoughProductsInStockException extends RuntimeException{

    public NotEnoughProductsInStockException(){
    }

    public NotEnoughProductsInStockException(String message){
        super(message);
    }

}
