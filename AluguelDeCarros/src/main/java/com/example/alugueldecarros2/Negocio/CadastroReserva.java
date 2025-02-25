package com.example.alugueldecarros2.Negocio;

import com.example.alugueldecarros2.Dados.CarroRepositorio;
import com.example.alugueldecarros2.Dados.ContasRepositorio;
import com.example.alugueldecarros2.Dados.ReservaRepositorio;
import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.DataInvalidaException;
import com.example.alugueldecarros2.Exceptions.OperacaoBemSucedidaException;
import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
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
    private CarroRepositorio carroRepositorio;
    private static CadastroReserva instance;

    private CadastroReserva(){
        this.reservaRepositorio = ReservaRepositorio.getInstance();
        this.ultimoIdReserva = reservaRepositorio.getMaiorId();
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
            throws DataInvalidaException, CarroNaoExisteException, OperacaoInvalidaException {

        Carro auxCarro = this.carroRepositorio.buscarCarroPorId(carro.getIdCarro());

        if(auxCarro != null && datafinal.isAfter(datainicio)){
            Reserva[] reservas = this.buscarReservasPorCarro(auxCarro.getIdCarro());
            Reserva reserva = new Reserva(carro, cliente, datainicio, datafinal, formaDePagamento);

            if(!this.verificarValidadeDasDatas(reservas, reserva)){
                throw new OperacaoInvalidaException();
            }

            reserva.setNumero(ultimoIdReserva + 1);
            ultimoIdReserva++;
            reservaRepositorio.adicionarReserva(reserva);
            return reserva;
        }
        else {
            throw new DataInvalidaException();
        }
    }

    private boolean verificarValidadeDasDatas(Reserva[] listaReservas, Reserva reserva){
        for(int i = 0; i < listaReservas.length; i++){
            if(listaReservas[i] != null){
                if(listaReservas[i].getDataInicio().isBefore(reserva.getDataFinal()) &&
                        listaReservas[i].getDataInicio().isAfter(reserva.getDataInicio())){
                    if(listaReservas[i].getDataFinal().isBefore(reserva.getDataFinal()) &&
                            listaReservas[i].getDataFinal().isAfter(reserva.getDataInicio())){
                        return false;
                    }
                }
            }
        }

        return true;
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



    public Reserva[] getListaInicialReservas(){
        return reservaRepositorio.getListaInicialReservas();
    }



    public Reserva[] getListaReservas(String categoria, String faixaDePreco)
            throws OperacaoInvalidaException{
        if(categoria == null || faixaDePreco == null){
            throw new OperacaoInvalidaException();
        }

        if(categoria.equals("Qualquer categoria") && faixaDePreco.equals("Qualquer preço")){
            throw new OperacaoInvalidaException();
        }

        if(categoria.equals("Qualquer categoria")){
            return reservaRepositorio.getListaReservasPorPreco(faixaDePreco);
        }

        if(faixaDePreco.equals("Qualquer preço")){
            return reservaRepositorio.getListaReservasPorCategoria(categoria);
        }
        if(!categoria.equals("Hatchback") && !categoria.equals("Sedan") &&
                !categoria.equals("Pickup") && !categoria.equals("SUV")){

            throw new OperacaoInvalidaException();
        }

        if(!faixaDePreco.equals("Popular") && !faixaDePreco.equals("Médio") &&
                !faixaDePreco.equals("Luxo")){

            throw new OperacaoInvalidaException();
        }

        return this.reservaRepositorio.getListaReservas(categoria, faixaDePreco);
    }


    public Reserva[] getListaReservasAPartirDaData(
            LocalDate dataInicial, String categoria, String faixaDePreco) {

        Reserva[] reservasAux = null;
        Reserva[] resultado = null;

        try {
            reservasAux = this.getListaReservas(categoria, faixaDePreco);
        } catch (OperacaoInvalidaException ex) {
            reservasAux = this.getListaInicialReservas();
        }

        resultado = new Reserva[reservasAux.length];

        int auxIndex = 0;

        for(int i = 0; i < reservasAux.length; i++){
            if(reservasAux[i] != null) {
                try {
                    this.verificarDisponibilidadeAPartirDaData(dataInicial, reservasAux[i]);
                } catch (OperacaoBemSucedidaException e) {
                    resultado[auxIndex] = reservasAux[i];
                    auxIndex++;
                }
            }
        }

        return resultado;
    }




    private void verificarDisponibilidadeAPartirDaData(LocalDate dataInicial, Reserva reserva)
            throws OperacaoBemSucedidaException{

        boolean sucesso = true;

        if(reserva != null && !reserva.getDataFinal().isAfter(dataInicial)){
            sucesso = false;
        }

        if(sucesso){
            throw new OperacaoBemSucedidaException();
        }
    }



    public Reserva[] getListaReservasNoPeriodo(LocalDate dataInicial, LocalDate dataFinal,
                                           String categoria, String faixaDePreco) {

        Reserva[] reservasAux = null;
        Reserva[] resultado = null;

        try {
            reservasAux = this.getListaReservas(categoria, faixaDePreco);
        } catch (OperacaoInvalidaException ex) {
            reservasAux = this.getListaInicialReservas();
        }

        resultado = new Reserva[reservasAux.length];

        int auxIndex = 0;

        for(int i = 0; i < reservasAux.length; i++){
            try {
                this.verificarAmbasDisponibilidades(dataInicial, dataFinal, reservasAux[i]);
            } catch(OperacaoBemSucedidaException e){
                resultado[auxIndex] = reservasAux[i];
                auxIndex++;
            }
        }

        return resultado;
    }

    private void verificarAmbasDisponibilidades(LocalDate dataInicial, LocalDate dataFinal,
                                                Reserva reserva)
            throws OperacaoBemSucedidaException{

        Exception e1 = null;
        Exception e2 = null;

        try{
            this.verificarDisponibilidadeAPartirDaData(dataInicial, reserva);
        } catch(OperacaoBemSucedidaException e){
            e1 = e;
        }

        try{
            this.verificarDisponibilidadeAntesDaData(dataFinal, reserva);
        }catch(OperacaoBemSucedidaException e){
            e2 = e;
        }

        if(!(e1 == null || e2 == null)){
            throw new OperacaoBemSucedidaException();
        }
    }






    public Reserva[] getListaReservasAntesDaData(LocalDate dataFinal,
                                             String categoria, String faixaDePreco) {

        Reserva[] reservasAux = null;
        Reserva[] resultado = null;

        try {
            reservasAux = this.getListaReservas(categoria, faixaDePreco);
        } catch (OperacaoInvalidaException ex) {
            reservasAux = this.getListaInicialReservas();
            resultado = new Reserva[reservasAux.length];
        }

        int auxIndex = 0;

        for(int i = 0; i < reservasAux.length; i++){
            try {
                this.verificarDisponibilidadeAntesDaData(dataFinal, reservasAux[i]);
            } catch(OperacaoBemSucedidaException e){
                resultado[auxIndex] = reservasAux[i];
                auxIndex++;
            }
        }

        return resultado;
    }

    private void verificarDisponibilidadeAntesDaData(LocalDate dataFinal, Reserva reserva)
            throws OperacaoBemSucedidaException{

        boolean sucesso = true;

        if(reserva != null && !reserva.getDataInicio().isBefore(dataFinal)){
            sucesso = false;
        }

        if(sucesso){
            throw new OperacaoBemSucedidaException();
        }
    }







    public Reserva[] buscarReservasCliente(int idCliente) {
        return this.reservaRepositorio.buscarReservasPorCliente(idCliente);
    }

    public Reserva[] buscarReservasPorCarro(int IdCarro) {
        return this.reservaRepositorio.buscarReservasPorCarro(IdCarro);
    }


    public float[] getFaturamentoNoPeriodo(LocalDate inicio, LocalDate fim){
        return reservaRepositorio.getFaturamentoNoPeriodo(inicio, fim);
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
