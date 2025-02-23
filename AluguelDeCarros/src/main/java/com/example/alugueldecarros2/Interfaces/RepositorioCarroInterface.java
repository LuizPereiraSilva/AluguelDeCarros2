package com.example.alugueldecarros2.Interfaces;

import com.example.alugueldecarros2.Exceptions.Carros.CarroJaExisteException;
import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;

public interface RepositorioCarroInterface {

    public void adicionarCarro(Carro carro) throws RepositorioCheioException;

    public void removerCarro(String placa) throws CarroNaoExisteException;

    public Carro buscarCarroPorId(int idCarro) throws CarroNaoExisteException;

    public void atualizarCarro(Carro carro) throws CarroNaoExisteException;

    public void verificarPlaca(String placa) throws CarroJaExisteException;

    public Carro[] getListaCarros(String categoria, String faixaDePreco);

    public Carro[] getListaCarrosPorCategoria(String categoria);

    public Carro[] getListaCarrosPorPreco(String faixaDePreco);
}
