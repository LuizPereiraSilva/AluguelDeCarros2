package com.example.alugueldecarros2.Negocio;

import com.example.alugueldecarros2.Exceptions.Carros.CarroJaExisteException;
import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.Contas.ContaJaExisteException;
import com.example.alugueldecarros2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarros2.Exceptions.DataInvalidaException;
import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Exceptions.Reservas.NenhumaReservaException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Basico.Reserva;

import java.time.LocalDate;

public class Fachada {

    private CadastroConta contas;
    private CadastroCarro carros;
    private CadastroReserva reservas;
    private static Fachada instance;

    private Fachada(){
        this.contas = CadastroConta.getInstance();
        this.carros = CadastroCarro.getInstance();
        this.reservas = CadastroReserva.getInstance();
    }

    public static Fachada getInstance(){
        if(instance == null){
            instance = new Fachada();
        }
        return instance;
    }

    //Métodos de Cadastro

    public Conta getCadastro(){ return CadastroConta.getCadastro(); }

    public void setCadastro(Conta conta){ CadastroConta.setCadastro(conta); }

    //Métodos de Conta

    public void cadastrarCliente(String nome, String cpf, String telefone, String email, String senha) throws
            ContaJaExisteException, RepositorioCheioException{
        contas.cadastrarCliente(nome, cpf, telefone, email, senha);
    }

    public void cadastrarAdministrador(String nome, String cpf, String telefone, String email, String senha) throws
            ContaJaExisteException, RepositorioCheioException{
        contas.cadastrarAdministrador(nome, cpf, telefone, email, senha);
    }

    public void removerConta(int contaId) throws ContaNaoExisteException{
        contas.removerConta(contaId);
    }

    public Conta buscarConta(int contaId) throws ContaNaoExisteException{
        return contas.buscarConta(contaId);
    }

    public Conta buscarContaPeloCpf(String cpf) throws ContaNaoExisteException{
        return contas.buscarContaPeloCpf(cpf);
    }

    public void atualizarConta(String nome, String cpf, String telefone, String email, String senha)
            throws ContaNaoExisteException{
        contas.atualizarConta(nome, cpf, telefone, email, senha);
    }

    public String listarContas(){
        return contas.listarContas();
    }

    //Métodos de Carro

    public void cadastrarCarro(int modelo, float preco, String placa, String caracteristicas)
            throws RepositorioCheioException, CarroJaExisteException {
        carros.cadastrarCarro(modelo, preco, placa, caracteristicas);
    }

    public void removerCarro(int id) throws CarroNaoExisteException{
        carros.removerCarro(id);
    }

    public Carro buscarCarro(int id) throws CarroNaoExisteException {
        return carros.buscarCarro(id);
    }

    public Carro buscarCarroPorPlaca(String placa) throws CarroNaoExisteException {
        return carros.buscarCarroPorPlaca(placa);
    }

    public void atualizarPreco(int id, float novoPreco) throws CarroNaoExisteException{
        carros.atualizarPreco(id, novoPreco);
    }

    public Carro[] getListaInicialCarros(){ return carros.getListaInicialCarros(); }

    public Carro[] getListaCarros(String tipo, String faixaDePreco) throws OperacaoInvalidaException {
        return carros.getListaCarros(tipo, faixaDePreco);
    }

    public String listarCarros(){
        return carros.listarCarros();
    }

    //Métodos de Reserva

    public void cadastrarReserva(Carro carro, Conta cliente, LocalDate dataInicio, LocalDate dataFinal,
                                 String formaDePagamento) throws DataInvalidaException, CarroNaoExisteException{
        reservas.cadastrarReserva(carro, cliente, dataInicio, dataFinal, formaDePagamento);
    }

    public void removerReserva(int idReserva){
        reservas.removerReserva(idReserva);
    }

    public Reserva buscarReserva(int idReserva){
        return reservas.buscarReserva(idReserva);
    }

    public void atualizarReserva(int idReserva, Carro carro, Conta cliente, LocalDate dataInicio,
                                 LocalDate dataFinal, String formaDePagamento) throws CarroNaoExisteException{
        reservas.atualizarReserva(idReserva, carro, cliente, dataInicio, dataFinal, formaDePagamento);
    }

    public String buscarReservasCliente(int idCliente) throws NenhumaReservaException {
        return reservas.buscarReservasCliente(idCliente);
    }

    public String buscarReservasCarro(int IdCarro) throws NenhumaReservaException {
        return this.reservas.buscarReservasPorCarro(IdCarro);
    }

    public String buscarReservasPeriodo(LocalDate dataInicio, LocalDate dataFinal) throws NenhumaReservaException {
        return this.reservas.buscarReservasPeriodo(dataInicio, dataFinal);
    }

    public String listarReservas(){
        return reservas.toString();
    }

    public String relatorioReservas(int IdCliente) throws NenhumaReservaException{ return reservas.gerarRelatorioCompleto(IdCliente);}

    public String gerarFaturamentoNoPeriodo(LocalDate datainicio, LocalDate datafinal) throws NenhumaReservaException, DataInvalidaException{
        return reservas.gerarFaturamentoPeriodo(datainicio, datafinal);
    }
}
