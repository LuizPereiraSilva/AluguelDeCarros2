package com.example.alugueldecarros2.Negocio.Basico;

public class Administrador extends Conta {

    public Administrador(String nome, String cpf, String telefone){
        super(nome, cpf, telefone, true);
    }
}
