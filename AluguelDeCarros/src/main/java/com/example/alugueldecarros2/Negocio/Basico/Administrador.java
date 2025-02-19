package com.example.alugueldecarros2.Negocio.Basico;

public class Administrador extends Conta {

    public Administrador(String nome, String cpf, String telefone, String email, String senha){
        super(nome, cpf, telefone, email, senha, true);
    }
}
