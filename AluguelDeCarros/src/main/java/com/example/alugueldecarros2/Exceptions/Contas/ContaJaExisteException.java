package com.example.alugueldecarros2.Exceptions.Contas;

public class ContaJaExisteException extends Exception{
    public ContaJaExisteException(){
        super("Conta já existe no repositorio");
    }
}
