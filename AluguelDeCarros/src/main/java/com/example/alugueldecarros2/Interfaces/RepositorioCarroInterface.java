package com.example.alugueldecarros2.Interfaces;

import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;

public interface RepositorioCarroInterface {

    public void adicionarCarro(Carro carro) throws RepositorioCheioException;

    public void removerCarro(int idCarro) throws CarroNaoExisteException;

    public Carro buscarCarroPorId(int idCarro) throws CarroNaoExisteException;

    public Carro[] buscarCarrosPorModelo(int modelo);

    public void atualizarPreco(int idCarro, float novoPreco) throws CarroNaoExisteException;
}
