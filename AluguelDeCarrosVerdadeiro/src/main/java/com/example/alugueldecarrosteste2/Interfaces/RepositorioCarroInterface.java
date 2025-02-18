package com.example.alugueldecarrosteste2.Interfaces;

import com.example.alugueldecarrosteste2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarrosteste2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarrosteste2.Negocio.Basico.Carro;

public interface RepositorioCarroInterface {

    public void adicionarCarro(Carro carro) throws RepositorioCheioException;

    public void removerCarro(int idCarro) throws CarroNaoExisteException;

    public Carro buscarCarroPorId(int idCarro) throws CarroNaoExisteException;

    public Carro[] buscarCarrosPorModelo(int modelo);

    public void atualizarPreco(int idCarro, float novoPreco) throws CarroNaoExisteException;
}
