package com.example.alugueldecarros2.Negocio.Basico;

import java.io.Serializable;

public abstract class Conta implements Serializable{

    private String nome;
    private int idConta;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private boolean administrador;

    public Conta(String nome, String cpf, String telefone, String email, String senha, boolean administrador){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
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

    public void setEmail(String email){ this.email = email; }

    public String getEmail(){ return this.email; }

    public String getSenha(){
        return this.senha;
    }

    public void setSenha(String senha){ this.senha = senha; }


    public boolean getAdministrador(){ return administrador; }


    public boolean compareTo(Conta conta){
        if(this.idConta == conta.idConta){
            return true;
        }
        return false;
    }


    public String adicionarNaLista(){
        if(this.administrador) {
            return "Administrador: " + this.getNome() + " / ID: " + this.getIdConta() + " / CPF: " + this.getCpf();
        } else {
            return "Cliente: " + this.getNome()+ " / ID: " + this.getIdConta() + " / CPF: " + this.getCpf();
        }
    }


    @Override
    public String toString(){
        if(this.administrador) {
            return "Administrador: " + this.getNome() + "\nID: " + this.getIdConta() + "\nCPF: " + this.getCpf() + "\nTelefone: " + this.getTelefone() + "\nEmail: " + this.getEmail() + "\nSenha: " + this.getSenha();
        } else {
            return "Cliente: " + this.getNome()+ "\nID: " + this.getIdConta() + "\nCPF: " + this.getCpf() + "\nTelefone: " + this.getTelefone() + "\nEmail: " + this.getEmail() + "\nSenha: " + this.getSenha();
        }
    }

}
