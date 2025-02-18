package com.example.alugueldecarrosteste2.Interfaces;

import com.example.alugueldecarrosteste2.Exceptions.Contas.ContaJaExisteException;
import com.example.alugueldecarrosteste2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarrosteste2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarrosteste2.Negocio.Basico.Conta;

public interface RepositorioContasInterface {

    public void adicionarConta(Conta conta) throws RepositorioCheioException, ContaJaExisteException;

    public void removerConta(int idConta) throws ContaNaoExisteException;

    public Conta buscarConta(int idConta) throws ContaNaoExisteException;

    public void atualizarConta(Conta conta) throws ContaNaoExisteException;
}
