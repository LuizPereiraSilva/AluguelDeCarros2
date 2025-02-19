package com.example.alugueldecarros2.Negocio.Basico;

public class Cliente extends Conta {

    public Cliente(String nome, String cpf, String telefone, String senha){
        super(nome, cpf, telefone,senha, false);
    }


}
