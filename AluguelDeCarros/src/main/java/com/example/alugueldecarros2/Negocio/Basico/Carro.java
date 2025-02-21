package com.example.alugueldecarros2.Negocio.Basico;

import java.io.Serializable;

public class Carro implements Serializable{

    private String modelo;
    private int idModelo;
    private int idCarro; // A placa do carro
    private float preco;
    private String caracteristicas;
    private String placa;
    private boolean disponivel;
    
    public Carro(int modelo, int idCarro, float preco, String placa, String caracteristicas) {
        this.setIdModelo(modelo);
        this.idCarro = idCarro;
        this.preco = preco;
        this.placa  = placa;
        this.caracteristicas = caracteristicas;
    }

    // Métodos get set
    public int getIdModelo(){ return this.idModelo; }

    public void setIdModelo(int id){
        this.idModelo = id;

        switch(id){
            case 1:
                this.modelo = "Hatchback";
                break;

            case 2:
                this.modelo = "Sedan";
                break;

            case 3:
                this.modelo = "Pickup";
                break;

            case 4:
                this.modelo = "SUV";
                break;

            default:
                this.modelo = null;
                break;
        }
    }

    public String getModelo() {
        return modelo;
    }

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

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }

    public boolean getDisponivel(){ return this.disponivel; }

    // metodo para ver informacao do carro
    public String toString() {
        String resultado = "\n\nCarro " + this.idCarro + ": ";
        resultado += " \nModelo: " + this.modelo;
        resultado += " \nID do carro: " + this.idCarro;
        resultado += " \nPreço: R$ " + this.preco;
        resultado += " \nCaracterísticas: " + this.caracteristicas;

        return resultado;
    }

    public String adicionarNaLista(){
        String resultado = "";
        resultado += "Marca e Modelo:" + this.caracteristicas;
        resultado += " / Tipo de Carro: " + this.modelo;
        resultado += "\nPreco: R$" + this.preco;

        return resultado;
    }
}
