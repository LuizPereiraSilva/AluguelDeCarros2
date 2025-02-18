package com.example.alugueldecarrosteste2.Exceptions.Contas;

public class ContaNaoExisteException extends Exception{
    public ContaNaoExisteException(){
        super("Conta não existe");
    }
}
