package com.example.alugueldecarros2.Interfaces;

import com.example.alugueldecarros2.Exceptions.Carros.CarroJaExisteException;
import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;

public interface RepositorioCarroInterface {


    //Métodods de modificação de itens da lista.

    void adicionarCarro(Carro carro) throws RepositorioCheioException;

    void removerCarro(String placa) throws CarroNaoExisteException;

    void atualizarCarro(Carro carro) throws CarroNaoExisteException;

    void atualizarDisponibilidadeCarro(String placa, boolean disponivel) throws CarroNaoExisteException;

    void verificarPlaca(String placa) throws CarroJaExisteException;


    //Métodos de obtenção de informações da lista.

    int getMaiorIdCarro();


    //Métodos de obtenção de itens da lista.

    Carro buscarCarroPorId(int idCarro) throws CarroNaoExisteException;

    Carro buscarCarroPorPlaca(String placa) throws CarroNaoExisteException;

    Carro[] getListaCarros(String categoria, String faixaDePreco);

    Carro[] getListaCarrosPorCategoria(String categoria);

    Carro[] getListaCarrosPorPreco(String faixaDePreco);

    Carro[] getListaInicialCarros();
}
