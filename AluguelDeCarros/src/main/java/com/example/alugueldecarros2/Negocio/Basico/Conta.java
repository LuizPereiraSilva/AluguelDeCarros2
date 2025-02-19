package com.example.alugueldecarros2.Negocio.Basico;

import java.io.Serializable;

public abstract class Conta implements Serializable{

    private String nome;
    private int idConta;
    private String cpf;
    private String telefone;
    private String senha;
    private boolean administrador;

    public Conta(String nome, String cpf, String telefone, boolean administrador){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.administrador = administrador;
    }

    public Conta(){}

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getIdConta(){
        return this.idConta;
    }

    public void setIdConta(int id){
        this.idConta = id;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void setCpf(String CPF){
        this.cpf = CPF;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getSenha(){
        return this.senha;
    }

    public boolean getAdministrador(){return administrador;}
    
    @Override
    public String toString(){
        if(this.administrador) {
            return "Administrador: " + this.getNome() + "\nID: " + this.getIdConta() + "\nCPF: " + this.getCpf() + "\nTelefone: " + this.getTelefone();
        } else {
            return "Cliente: " + this.getNome()+ "\nID: " + this.getIdConta() + "\nCPF: " + this.getCpf() + "\nTelefone: " + this.getTelefone();
        }
    }

}
