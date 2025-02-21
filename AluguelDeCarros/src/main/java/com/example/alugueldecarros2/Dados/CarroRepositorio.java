package com.example.alugueldecarros2.Dados;

import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
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
    public void removerCarro(int idCarro)  throws CarroNaoExisteException{
        int aux = this.buscarIndexCarro(idCarro);

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

    // buscar carro por modelo
    public Carro[] buscarCarrosPorModelo(int modelo) {
        Carro[] carrosEncontrados = new Carro[this.tamanho];
        int j = 0;

        for (int i = 0; i < this.carrosIndex; i++) {
            if (carros[i].getIdModelo() == modelo) {
                carrosEncontrados[j] = carros[i];
                j++;
            }
        }

        return carrosEncontrados;
    }

    // atualizar o preço
    public void atualizarPreco(int idCarro, float novoPreco) throws CarroNaoExisteException{
        Carro carro = buscarCarroPorId(idCarro);
        if (carro != null) {
            carro.setPreco(novoPreco);
        }

        escreverArquivo();
    }

    public String[] getListaCarros(){
        String[] resultado = new String[this.carrosIndex];

        for(int i = 0; i < carrosIndex; i++){
            resultado[i] = carros[i].adicionarNaLista();
        }

        return resultado;
    }

    public String toString(){
        String resultado = "\n\nLista de carros: \n";

        for(int i = 0; i < this.carrosIndex; i++){
            resultado += carros[i].toString() + "\n";
        }

        return resultado;
    }
}
