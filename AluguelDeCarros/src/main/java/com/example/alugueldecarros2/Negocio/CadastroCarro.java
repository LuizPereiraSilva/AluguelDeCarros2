package com.example.alugueldecarros2.Negocio;

import com.example.alugueldecarros2.Dados.CarroRepositorio;
import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.Carros.CarroJaExisteException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;

public class CadastroCarro {

    private int ultimoId;
    private CarroRepositorio repositorio;
    private static CadastroCarro instance;

    private CadastroCarro(){
        this.repositorio = CarroRepositorio.getInstance();
        this.ultimoId = repositorio.getMaiorIdCarro();
    }

    protected static CadastroCarro getInstance(){
        if (instance == null) {
            instance = new CadastroCarro();
        }

        return instance;
    }

    public void cadastrarCarro(int modelo, float preco, String placa, String caracteristicas)
            throws RepositorioCheioException, CarroJaExisteException {
        repositorio.verificarPlaca(placa);
        if (0 < modelo && modelo < 5 && preco > 0) {
            Carro carro = new Carro(modelo, this.ultimoId + 1, preco, placa, caracteristicas);
            this.repositorio.adicionarCarro(carro);
            ultimoId++;
        }
    }

    public void removerCarro(int id) throws CarroNaoExisteException {
        this.repositorio.removerCarro(id);
    }

    public Carro buscarCarro(int id) throws CarroNaoExisteException{
        return this.repositorio.buscarCarroPorId(id);
    }

    public Carro buscarCarroPorPlaca(String placa) throws CarroNaoExisteException{
        return this.repositorio.buscarCarroPorPlaca(placa);
    }

    public void atualizarPreco(int id, float novoPreco) throws CarroNaoExisteException{
        if(novoPreco > 0) {
            this.repositorio.atualizarPreco(id, novoPreco);
        }
    }

    public Carro[] getListaCarros(){
        return this.repositorio.getListaCarros();
    }

    public String listarCarros(){
        return this.repositorio.toString();
    }
}
