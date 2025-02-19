package com.example.alugueldecarros2.Negocio.Basico;

public class Cliente extends Conta {

    public Cliente(String nome, String cpf, String telefone,String email, String senha){
        super(nome, cpf, telefone, email, senha, false);
    }


}
