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

    public void cadastrarCliente(String nome, String cpf, String telefone, String senha) throws
            ContaJaExisteException, RepositorioCheioException{

        Conta cliente = new Cliente(nome, cpf, telefone, senha);
        cliente.setIdConta(this.ultimoId + 1);
        this.ultimoId++;
        this.repositorio.adicionarConta(cliente);
    }

    public void cadastrarAdministrador(String nome, String cpf, String telefone, String senha) throws
            ContaJaExisteException, RepositorioCheioException{

        Conta administrador = new Administrador(nome, cpf, telefone, senha);
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

    public void atualizarConta(String nome, String cpf, String telefone, String senha) throws
            ContaNaoExisteException{

        Conta conta = repositorio.buscarPeloCpf(cpf);

        if(conta != null) {
            if (conta.getAdministrador()) {
                Conta administrador = new Administrador(nome, cpf, telefone, senha);
                administrador.setIdConta(conta.getIdConta());
                repositorio.atualizarConta(administrador);
            } else {
                Conta cliente = new Cliente(nome, cpf, telefone, senha);
                cliente.setIdConta(conta.getIdConta());
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
