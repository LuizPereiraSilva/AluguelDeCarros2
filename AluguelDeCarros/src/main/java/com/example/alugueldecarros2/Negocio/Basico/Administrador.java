package com.example.alugueldecarros2.Negocio.Basico;

public class Administrador extends Conta {

    public Administrador(String nome, String cpf, String telefone, String senha){
        super(nome, cpf, telefone, senha, true);
    }
}
