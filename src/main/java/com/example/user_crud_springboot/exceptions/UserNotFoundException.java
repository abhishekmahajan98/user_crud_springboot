package com.example.user_crud_springboot.exceptions;


public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
    }
}
