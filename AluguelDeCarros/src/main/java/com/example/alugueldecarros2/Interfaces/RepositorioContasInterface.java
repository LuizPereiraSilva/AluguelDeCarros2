package com.example.alugueldecarros2.Interfaces;

import com.example.alugueldecarros2.Exceptions.Contas.ContaJaExisteException;
import com.example.alugueldecarros2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Basico.Conta;

public interface RepositorioContasInterface {

    public void adicionarConta(Conta conta) throws RepositorioCheioException, ContaJaExisteException;

    public void removerConta(int idConta) throws ContaNaoExisteException;

    public Conta buscarConta(int idConta) throws ContaNaoExisteException;

    public Conta buscarPeloCpf(String cpf) throws ContaNaoExisteException;

    public void atualizarConta(Conta conta) throws ContaNaoExisteException;
}
