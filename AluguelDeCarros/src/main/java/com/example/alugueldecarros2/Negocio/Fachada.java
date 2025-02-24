package com.example.alugueldecarros2.Negocio;

import com.example.alugueldecarros2.Exceptions.Carros.CarroJaExisteException;
import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.Contas.ContaJaExisteException;
import com.example.alugueldecarros2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarros2.Exceptions.DataInvalidaException;
import com.example.alugueldecarros2.Exceptions.OperacaoBemSucedidaException;
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

    public void cadastrarCarro(String categoria, float preco, String placa, String modelo, String marca)
            throws RepositorioCheioException, CarroJaExisteException, OperacaoInvalidaException,
            OperacaoBemSucedidaException {
        carros.cadastrarCarro(categoria, preco, placa, modelo, marca);
    }

    public void removerCarro(String placa) throws CarroNaoExisteException{
        carros.removerCarro(placa);
    }

    public void atualizarCarro(String categoria, float preco, String placa, String modelo,
                               String marca) throws CarroNaoExisteException, OperacaoInvalidaException{
        carros.atualizarPreco(categoria, preco, placa, modelo, marca);
    }





    public Carro buscarCarro(int id) throws CarroNaoExisteException {
        return carros.buscarCarro(id);
    }

    public Carro buscarCarroPorPlaca(String placa) throws CarroNaoExisteException {
        return carros.buscarCarroPorPlaca(placa);
    }






    public Carro[] getListaCarrosAPartirDaData(LocalDate dataInicial,
                                               String categoria,
                                               String faixaDePreco)
            throws OperacaoInvalidaException{
        return carros.getListaCarrosAPartirDaData(dataInicial, categoria, faixaDePreco); }


    public Carro[] getListaCarrosAntesDaData(LocalDate dataFinal,
                                             String categoria,
                                             String faixaDePreco)
            throws OperacaoInvalidaException{
        return carros.getListaCarrosAntesDaData(dataFinal, categoria, faixaDePreco); }


    public Carro[] getListaCarrosNoPeriodo(LocalDate dataInicial, LocalDate dataFinal,
                                           String categoria, String faixaDePreco)
            throws OperacaoInvalidaException{
        return carros.getListaCarrosNoPeriodo(dataInicial, dataFinal, categoria, faixaDePreco); }



    public Carro[] getListaInicialCarros(){ return carros.getListaInicialCarros(); }


    public Carro[] getListaCarros(String categoria, String faixaDePreco) throws OperacaoInvalidaException {
        return carros.getListaCarros(categoria, faixaDePreco);
    }


    public String listarCarros(){
        return carros.listarCarros();
    }






    //Métodos de Reserva

    public Reserva cadastrarReserva(Carro carro, Conta cliente, LocalDate dataInicio,
                                 LocalDate dataFinal,
                                 String formaDePagamento)
            throws DataInvalidaException, CarroNaoExisteException{
        return reservas.cadastrarReserva(carro, cliente, dataInicio, dataFinal, formaDePagamento);
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






    public Reserva[] buscarReservasCliente(int idCliente){
        return reservas.buscarReservasCliente(idCliente);
    }

    public Reserva[] buscarReservasCarro(int IdCarro) {
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
