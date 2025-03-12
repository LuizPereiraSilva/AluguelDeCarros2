package com.example.alugueldecarros2.Negocio.Basico;

import java.io.Serializable;

public class Carro implements Serializable{

    private String categoria;
    private int idCategoria;
    private int idCarro; // A placa do carro
    private float preco;
    private String modelo;
    private String marca;
    private String placa;
    private boolean disponivel;
    
    public Carro(String categoria, int idCarro, float preco, String placa, String modelo, String marca) {
        this.categoria = categoria;
        this.idCarro = idCarro;
        this.preco = preco;
        this.placa  = placa;
        this.modelo = modelo;
        this.marca = marca;
    }

    public Carro(){}

    // Métodos get set

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String caracteristicas) {
        this.modelo = caracteristicas;
    }

    public String getMarca() { return marca; }

    public void setMarca(String Marca){ this.marca = marca; }

    public String getCategoria(){ return this.categoria; }

    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }

    public boolean getDisponivel(){ return this.disponivel; }

    // metodo para ver informacao do carro
    public String toString() {
        String resultado = "\n\nCarro " + this.idCarro + ": ";
        resultado += " \nModelo: " + this.categoria;
        resultado += " \nID do carro: " + this.idCarro;
        resultado += " \nPreço: R$ " + this.preco;
        resultado += " \nMarca: " + this.marca;
        resultado += " \nModelo: " + this.modelo;
        resultado += " \nPlaca: " + this.placa;

        return resultado;
    }

    public String adicionarNaLista(){
        String resultado = "";
        resultado += "Marca: " + this.marca;
        resultado += " / Modelo: " + this.modelo;
        resultado += " / Categoria de Carro: " + this.categoria;
        resultado += " / Placa do Carro: " + this.placa;
        resultado += "\nPreco: R$" + this.preco;

        return resultado;
    }

    public String adicionarNaListaAdm(){
        String resultado = "";
        resultado += "ID: " + this.idCarro;
        resultado += " / Marca: " + this.marca;
        resultado += " / Modelo: " + this.modelo;
        resultado += " / Categoria de Carro: " + this.categoria;
        resultado += " / Placa do Carro: " + this.placa;
        resultado += "\nPreco: R$" + this.preco;

        return resultado;
    }

    public String adicionarNaTabela(){
        String resultado = "";
        resultado += "Marca: " + this.marca;
        resultado += " / Modelo: " + this.modelo;
        resultado += "\nPlaca do Carro: " + this.placa;

        return resultado;
    }
}
