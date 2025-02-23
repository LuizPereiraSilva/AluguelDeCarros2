package com.example.alugueldecarros2.Dados;

import com.example.alugueldecarros2.Exceptions.Carros.CarroJaExisteException;
import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Interfaces.RepositorioCarroInterface;
import com.example.alugueldecarros2.Negocio.Basico.Carro;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CarroRepositorio implements RepositorioCarroInterface{

    private Carro[] carros; // lista dos carros
    private int carrosIndex;
    private int tamanho;
    private String arquivo;
    private static CarroRepositorio instance;

    // inicializa a lista dos carros
    private CarroRepositorio(int tamanho) {
        this.carros = new Carro[tamanho];
        this.carrosIndex = 0;
        this.arquivo = "CarroRepositorio.bin";
        this.tamanho = tamanho;

        this.lerArquivo();
    }

    public static CarroRepositorio getInstance(){
        if(instance == null){
            instance = new CarroRepositorio(100);
        }

        return instance;
    }

    public int getMaiorIdCarro(){
        int auxId = 0;
        for(int i = 0; i < carrosIndex; i++){
            if(carros[i].getIdCarro() > auxId){
                auxId = carros[i].getIdCarro();
            }
        }

        return auxId;
    }

    private void lerArquivo(){
        int auxIndex = 0;
        Carro[] auxCarros = new Carro[tamanho];
        Object o = null;

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));

            for(int i = 0; i < tamanho; i++){
                if((o = ois.readObject()) != null){
                    auxCarros[i] = (Carro) o;
                    auxIndex++;
                } else{
                    break;
                }
            }

            ois.close();
        } catch(Exception e){

        }

        this.carros = auxCarros;
        this.carrosIndex = auxIndex;
    }

    public void escreverArquivo(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));

            for(int i = 0; i < carrosIndex; i++){
                oos.writeObject(carros[i]);
            }

            oos.close();
        } catch(Exception e){

        }
    }

    // adicionar carro
    public void adicionarCarro(Carro carro) throws RepositorioCheioException {
        if(this.carrosIndex < tamanho){
            carros[carrosIndex] = carro;
            carrosIndex++;
            this.escreverArquivo();
        } else{
            throw new RepositorioCheioException();
        }
    }

    // remover carro pelo id
    public void removerCarro(String placa)  throws CarroNaoExisteException{
        int aux = this.buscarIndexCarro(this.buscarCarroPorPlaca(placa).getIdCarro());

        for(int i = aux; i < carrosIndex; i++){
            if(i < carrosIndex-1) {
                this.carros[i] = this.carros[i+1];
            } else{
                this.carros[i] = null;
            }
        }

        this.carrosIndex--;
        this.escreverArquivo();
    }

    // buscar carro pelo id (placa)
    public Carro buscarCarroPorId(int idCarro) throws CarroNaoExisteException{
        int aux = buscarIndexCarro(idCarro);

        if(aux != -1){
            return carros[aux];
        }

        throw new CarroNaoExisteException();
    }

    private int buscarIndexCarro(int idCarro) throws CarroNaoExisteException{
        for(int i = 0; i < this.tamanho; i++){
            if(carros[i].getIdCarro() == idCarro){
                return i;
            }
        }
        throw new CarroNaoExisteException();
    }

    public Carro buscarCarroPorPlaca(String placa) throws CarroNaoExisteException{
        for(int i = 0; i < carrosIndex; i++){
            if(carros[i].getPlaca().equals(placa)){
                return carros[i];
            }
        }

        throw new CarroNaoExisteException();
    }

    public void verificarPlaca(String placa) throws CarroJaExisteException {
        for(int i = 0; i < carrosIndex; i++){
            if(carros[i].getPlaca().equals(placa)){
                throw new CarroJaExisteException();
            }
        }
    }

    // atualizar o preço
    public void atualizarCarro(Carro carro) throws CarroNaoExisteException{
        int auxInt = buscarCarroPorPlaca(carro.getPlaca()).getIdCarro();
        carro.setIdCarro(auxInt);
        carros[this.buscarIndexCarro(auxInt)] = carro;

        escreverArquivo();
    }

    public Carro[] getListaCarros(String tipo, String faixaDePreco){
        Carro[] resultado = new Carro[this.carrosIndex];
        int j = 0;
        float precoMaisBaixo = 0;
        float precoMaisAlto = 0;

        switch(faixaDePreco){
            case "Popular":
                precoMaisAlto = 150;
                break;
            case "Médio":
                precoMaisBaixo = 151;
                precoMaisAlto = 500;
                break;
            case "Luxo":
                precoMaisBaixo = 501;
                precoMaisAlto = 1000000;
                break;
        }

        for (int i = 0; i < this.carrosIndex; i++) {
            if(this.carros[i].getModelo().equals(tipo) &&
                    this.carros[i].getPreco() > precoMaisBaixo &&
                    this.carros[i].getPreco()< precoMaisAlto){

                resultado[j] = this.carros[i];
                j++;
            }
        }

        return resultado;
    }

    public Carro[] getListaCarrosPorCategoria(String categoria){
        Carro[] carrosEncontrados = new Carro[this.carrosIndex];
        int j = 0;
        for(int i = 0; i < carrosIndex; i++){
            if(carros[i].getModelo().equals(categoria)) {
                carrosEncontrados[j] = carros[i];
                j++;
            }
        }

        return carrosEncontrados;
    }

    public Carro[] getListaCarrosPorPreco(String faixaDePreco){
        Carro[] carrosEncontrados = new Carro[this.carrosIndex];

        float precoMaisBaixo = 0;
        float precoMaisAlto = 0;

        switch(faixaDePreco){
            case "Popular":
                precoMaisAlto = 150;
                break;
            case "Médio":
                precoMaisBaixo = 151;
                precoMaisAlto = 500;
                break;
            case "Luxo":
                precoMaisBaixo = 501;
                precoMaisAlto = 1000000;
                break;
        }

        int j = 0;

        for(int i = 0; i < carrosIndex; i++){
            if(carros[i].getPreco()> precoMaisBaixo && carros[i].getPreco() < precoMaisAlto) {
                carrosEncontrados[j] = carros[i];
                j++;
            }
        }

        return carrosEncontrados;
    }

    public Carro[] getListaInicialCarros(){
        int j = 0;
        Carro[] lista = new Carro[this.carrosIndex];

        System.out.println("Bucetaaaa ");

        for(int i = 0; i < this.carrosIndex; i++){
            System.out.println("Filho da puta");
            if(carros[i] != null){
                System.out.println("puta que pariu");
                lista[j] = carros[i];
                j++;
            }
        }

        return lista;
    }

    public String toString(){
        String resultado = "\n\nLista de carros: \n";

        for(int i = 0; i < this.carrosIndex; i++){
            resultado += carros[i].toString() + "\n";
        }

        return resultado;
    }
}
