package com.example.alugueldecarros2.Interfaces;

import com.example.alugueldecarros2.Exceptions.Contas.ContaJaExisteException;
import com.example.alugueldecarros2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Basico.Conta;

public interface RepositorioContasInterface {


    //Métodos de modificação de itens da lista.

    void adicionarConta(Conta conta) throws RepositorioCheioException, ContaJaExisteException;

    void removerConta(int idConta) throws ContaNaoExisteException;

    void atualizarConta(Conta conta) throws ContaNaoExisteException;


    //Métodos de obtenção de informações da lista.

    int getMaiorId();


    //Métodos de obtenção de itens da lista.

    Conta buscarConta(int idConta) throws ContaNaoExisteException;

    Conta buscarPeloCpf(String cpf) throws ContaNaoExisteException;

    Conta[] getListaContas();
}
