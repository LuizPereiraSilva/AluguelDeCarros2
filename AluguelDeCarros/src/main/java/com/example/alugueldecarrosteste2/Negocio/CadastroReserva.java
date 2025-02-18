package com.example.alugueldecarrosteste2.Negocio;

import com.example.alugueldecarrosteste2.Dados.CarroRepositorio;
import com.example.alugueldecarrosteste2.Dados.ContasRepositorio;
import com.example.alugueldecarrosteste2.Dados.ReservaRepositorio;
import com.example.alugueldecarrosteste2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarrosteste2.Exceptions.DataInvalidaException;
import com.example.alugueldecarrosteste2.Exceptions.Reservas.NenhumaReservaException;
import com.example.alugueldecarrosteste2.Negocio.Basico.Carro;
import com.example.alugueldecarrosteste2.Negocio.Basico.Conta;
import com.example.alugueldecarrosteste2.Negocio.Basico.Reserva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class CadastroReserva {

    private int ultimoIdReserva;
    private ReservaRepositorio reservaRepositorio;
    private ContasRepositorio contasRepositorio;
    private CarroRepositorio carroRepositorio;
    private static CadastroReserva instance;

    private CadastroReserva(){
        this.ultimoIdReserva = 0;
        this.reservaRepositorio = ReservaRepositorio.getInstance();
        this.contasRepositorio = ContasRepositorio.getInstance();
        this.carroRepositorio = CarroRepositorio.getInstance();
    }

    public static CadastroReserva getInstance(){
        if(instance == null){
            instance = new CadastroReserva();
        }

        return instance;
    }

    public void cadastrarReserva(Carro carro, Conta cliente, LocalDate datainicio, LocalDate datafinal,
                                 String formaDePagamento) throws DataInvalidaException, CarroNaoExisteException {

        Carro auxCarro = this.carroRepositorio.buscarCarroPorId(carro.getIdCarro());

        if(auxCarro != null && datafinal.isAfter(datainicio)){
            Reserva reserva = new Reserva(carro, cliente, datainicio, datafinal, formaDePagamento);
            reserva.setNumero(ultimoIdReserva + 1);
            ultimoIdReserva++;
            reservaRepositorio.adicionarReserva(reserva);
        }
        else {
            throw new DataInvalidaException();
        }
    }

    public void removerReserva(int idReserva){
        this.reservaRepositorio.removerReserva(idReserva);
    }

    public Reserva buscarReserva(int idReserva){
        return this.reservaRepositorio.buscarReserva(idReserva);
    }

    public void atualizarReserva(int idReserva, Carro carro, Conta cliente, LocalDate dataInicio,
                                 LocalDate dataFinal, String formaDePagamento) throws CarroNaoExisteException{

        Carro auxCarro = this.carroRepositorio.buscarCarroPorId(carro.getIdCarro());
        Reserva auxReserva = this.reservaRepositorio.buscarReserva(idReserva);

        if(auxCarro != null && auxReserva != null){
            Reserva reserva = new Reserva(carro, cliente, dataInicio, dataFinal, formaDePagamento);
            reserva.setNumero(idReserva);
            reservaRepositorio.atualizarReserva(reserva);
        }
    }

    public String buscarReservasCliente(int idCliente) throws NenhumaReservaException {
       Reserva[] resultado = this.reservaRepositorio.buscarReservasPorCliente(idCliente);

        return "\n Reservas encontradas para esse Id: " + Arrays.toString(resultado);
    }

    public String buscarReservasPorCarro(int IdCarro) throws NenhumaReservaException {
        Reserva[] resultado = this.reservaRepositorio.buscarReservasPorCarro(IdCarro);

        return "\n Reservas encontradas para esse carro: " + Arrays.toString(resultado);
    }

    public String buscarReservasPeriodo(LocalDate dataInicio, LocalDate dataFinal) throws NenhumaReservaException {
        Reserva[] resultado = this.reservaRepositorio.buscarReservasPorPeriodo(dataInicio, dataFinal);

        return "\n Reservas encontradas para esse periodo: " + Arrays.toString(resultado);
    }

    public String toString(){
        return reservaRepositorio.toString();
    }

    public String gerarRelatorioCompleto(int IdCliente) throws NenhumaReservaException {

        return reservaRepositorio.gerarRelatorioPorCliente(IdCliente);
    }

    public String gerarFaturamentoPeriodo(LocalDate datainicio, LocalDate datafinal) throws NenhumaReservaException, DataInvalidaException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       return "Faturamento com reservas no período de " + datainicio.format(formatter) + " a " + datafinal.format(formatter) + " foi: R$ " + reservaRepositorio.gerarFaturamentoPorPeriodo(datainicio,datafinal);

    }

}
