package com.example.alugueldecarrosteste2.Exceptions;

public class LoginJaExisteException extends Exception{
    public LoginJaExisteException(){
        super("Login já existe");
    }
}
