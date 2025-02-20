package com.ecommerce.backend_ecommerce_amazon.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message)
    {
        super(message);
    }

}
