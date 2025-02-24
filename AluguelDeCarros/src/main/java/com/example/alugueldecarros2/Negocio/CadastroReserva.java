package com.example.alugueldecarros2.Negocio;

import com.example.alugueldecarros2.Dados.CarroRepositorio;
import com.example.alugueldecarros2.Dados.ContasRepositorio;
import com.example.alugueldecarros2.Dados.ReservaRepositorio;
import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.DataInvalidaException;
import com.example.alugueldecarros2.Exceptions.OperacaoBemSucedidaException;
import com.example.alugueldecarros2.Exceptions.Reservas.NenhumaReservaException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Basico.Reserva;

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
        this.reservaRepositorio = ReservaRepositorio.getInstance();
        this.ultimoIdReserva = reservaRepositorio.getMaiorId();
        this.contasRepositorio = ContasRepositorio.getInstance();
        this.carroRepositorio = CarroRepositorio.getInstance();
    }

    protected static CadastroReserva getInstance(){
        if(instance == null){
            instance = new CadastroReserva();
        }

        return instance;
    }

    public Reserva cadastrarReserva(Carro carro, Conta cliente, LocalDate datainicio, LocalDate datafinal,
                                 String formaDePagamento)
            throws DataInvalidaException, CarroNaoExisteException {

        Carro auxCarro = this.carroRepositorio.buscarCarroPorId(carro.getIdCarro());

        if(auxCarro != null && datafinal.isAfter(datainicio)){
            Reserva reserva = new Reserva(carro, cliente, datainicio, datafinal, formaDePagamento);
            reserva.setNumero(ultimoIdReserva + 1);
            ultimoIdReserva++;
            reservaRepositorio.adicionarReserva(reserva);
            return reserva;
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

    public Reserva[] buscarReservasCliente(int idCliente) {
        return this.reservaRepositorio.buscarReservasPorCliente(idCliente);
    }

    public Reserva[] buscarReservasPorCarro(int IdCarro) {
        return this.reservaRepositorio.buscarReservasPorCarro(IdCarro);
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
