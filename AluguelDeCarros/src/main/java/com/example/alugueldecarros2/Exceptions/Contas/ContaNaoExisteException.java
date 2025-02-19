package com.example.alugueldecarros2.Exceptions.Contas;

public class ContaNaoExisteException extends Exception{
    public ContaNaoExisteException(){
        super("Conta não existe");
    }
}
