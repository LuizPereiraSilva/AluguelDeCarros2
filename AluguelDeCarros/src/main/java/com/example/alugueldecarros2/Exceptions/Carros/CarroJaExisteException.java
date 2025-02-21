package com.example.alugueldecarros2.Exceptions.Carros;

public class CarroJaExisteException extends Exception{
    public CarroJaExisteException(){ super("Carro já existe "); }
}
