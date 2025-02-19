package com.example.alugueldecarros2.Negocio;

import com.example.alugueldecarros2.Dados.ContasRepositorio;
import com.example.alugueldecarros2.Exceptions.Contas.ContaJaExisteException;
import com.example.alugueldecarros2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Basico.Administrador;
import com.example.alugueldecarros2.Negocio.Basico.Cliente;
import com.example.alugueldecarros2.Negocio.Basico.Conta;

public class CadastroConta {

    private ContasRepositorio repositorio;
    private int ultimoId;
    private static CadastroConta instancia;

    private CadastroConta(){
        this.repositorio = ContasRepositorio.getInstance();
        this.ultimoId = repositorio.getMaiorId();
    }

    protected static CadastroConta getInstance(){
        if(instancia == null){
            instancia = new CadastroConta();
        }
        return instancia;
    }

    public void cadastrarCliente(String nome, String cpf, String telefone) throws
            ContaJaExisteException, RepositorioCheioException{

        Conta cliente = new Cliente(nome, cpf, telefone);
        cliente.setIdConta(this.ultimoId + 1);
        this.ultimoId++;
        this.repositorio.adicionarConta(cliente);
    }

    public void cadastrarAdministrador(String nome, String cpf, String telefone) throws
            ContaJaExisteException, RepositorioCheioException{

        Conta administrador = new Administrador(nome, cpf, telefone);
        administrador.setIdConta(this.ultimoId + 1);
        this.ultimoId++;
        this.repositorio.adicionarConta(administrador);
    }

    public void removerConta(int contaId) throws ContaNaoExisteException{
        repositorio.removerConta(contaId);
    }

    public Conta buscarConta(int contaId) throws ContaNaoExisteException{
        return repositorio.buscarConta(contaId);
    }

    public Conta buscarContaPeloCpf(String cpf) throws ContaNaoExisteException{
        return repositorio.buscarPeloCpf(cpf);
    }

    public void atualizarConta(String nome, int id, String cpf, String telefone) throws
            ContaNaoExisteException{

        Conta conta = repositorio.buscarConta(id);

        if(conta != null) {
            if (conta.getAdministrador()) {
                Conta administrador = new Administrador(nome, cpf, telefone);
                administrador.setIdConta(id);
                repositorio.atualizarConta(administrador);
            } else {
                Conta cliente = new Cliente(nome, cpf, telefone);
                cliente.setIdConta(id);
                repositorio.atualizarConta(cliente);
            }
        }
    }

    public int getUltimoId(){
        return this.ultimoId;
    }

    public String listarContas(){
        return repositorio.toString();
    }
}
