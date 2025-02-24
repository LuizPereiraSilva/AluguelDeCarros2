package com.example.alugueldecarros2.Dados;

import com.example.alugueldecarros2.Exceptions.DataInvalidaException;
import com.example.alugueldecarros2.Exceptions.Reservas.NenhumaReservaException;
import com.example.alugueldecarros2.Interfaces.RepositorioReservasInterface;
import com.example.alugueldecarros2.Negocio.Basico.Reserva;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class ReservaRepositorio implements RepositorioReservasInterface {

    private Reserva[] reservas;
    private int reservasIndex;
    private int tamanho;
    private String arquivo;
    private static ReservaRepositorio instance;

    private ReservaRepositorio(int tamanho) {
        this.reservas = new Reserva[tamanho];
        this.reservasIndex = 0;
        this.tamanho = tamanho;
        this.arquivo = "ReservaRepositorio.bin";

        this.lerArquivo();
    }

    public static ReservaRepositorio getInstance() {
        if (instance == null) {
            instance = new ReservaRepositorio(100);
        }
        return instance;
    }

    public int getMaiorId(){
        int auxId = 0;

        for(int i = 0; i < reservasIndex; i++){
            if(reservas[i].getNumero() > auxId){
                auxId = reservas[i].getNumero();
            }
        }

        return auxId;
    }

    public void lerArquivo(){
        Reserva[] auxReservas = new Reserva[tamanho];
        int auxIndex = 0;
        Object o = null;

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));
            for(int i = 0; i < tamanho; i++){
                if((o = ois.readObject()) != null){
                    auxReservas[auxIndex] = (Reserva) o;
                    auxIndex++;
                }else{
                    break;
                }
            }

            ois.close();
        } catch(Exception e){

        }

        reservas = auxReservas;
        reservasIndex = auxIndex;
    }

    public void escreverArquivo(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));

            for(int i = 0; i < reservasIndex; i++){
                oos.writeObject(reservas[i]);
            }

            oos.close();
        } catch(Exception e){

        }
    }

    public void adicionarReserva(Reserva reserva) {
        if (reservasIndex < this.tamanho) {
            this.reservas[reservasIndex] = reserva;
        }
        reservasIndex++;

        this.escreverArquivo();
    }

    private int buscarIndex(int numero) {
        for (int i = 0; i < this.reservasIndex; i++) {
            if (this.reservas[i].getNumero() == numero) {
                return i;
            }
        }
        return -1;
    }

    public void removerReserva(int idReserva) {
        int aux = this.buscarIndex(idReserva);

        if (aux != -1) {
            for (int i = aux; i < reservasIndex; i++) {
                if (i < reservasIndex - 1) {
                    this.reservas[i] = this.reservas[i + 1];
                } else {
                    this.reservas[i] = null;
                }
            }
        }

        reservasIndex--;
        this.escreverArquivo();
    }

    public Reserva buscarReserva(int numero) {
        int aux = this.buscarIndex(numero);

        if (aux != -1) {
            return this.reservas[aux];
        }
        return null;
    }

    public void atualizarReserva(Reserva reservaAtualizada) {
        int aux = this.buscarIndex(reservaAtualizada.getNumero());
        if (aux != -1) {
            this.reservas[aux] = reservaAtualizada;
        }

        this.escreverArquivo();
    }

    public Reserva[] buscarReservasPorCliente(int idCliente) {
        Reserva[] auxReservas = new Reserva[this.tamanho];
        int auxContador = 0;

        for (int i = 0; i < this.tamanho; i++) {
            if (this.reservas[i] != null && this.reservas[i].getCliente().getIdConta() == idCliente) {
                auxReservas[auxContador] = this.reservas[i];
                auxContador++;
            }
        }

        Reserva[] retorno = new Reserva[auxContador];

        for (int i = 0; i < auxContador; i++) {
            retorno[i] = auxReservas[i];
        }

        return retorno;
    }

    public Reserva[] buscarReservasPorCarro(int IdCarro) throws NenhumaReservaException {
        Reserva[] resultado = new Reserva[this.tamanho];
        int auxj = 0;

        for (int i = 0; i < this.tamanho; i++) {
            if (this.reservas[i] != null && this.reservas[i].getCarro().getIdCarro() == IdCarro) {
                resultado[auxj] = this.reservas[i];
                auxj++;
            }
        }
        if (auxj == 0) {
            throw new NenhumaReservaException();
        }

        Reserva[] resultado2 = new Reserva[auxj];

        for (int i = 0; i < auxj; i++) {
            resultado2[i] = resultado[i];
        }

        return resultado2;
    }

    public Reserva[] buscarReservasPorPeriodo(LocalDate datainicio, LocalDate datafinal) throws NenhumaReservaException {

        int encontradas = 0;

        for (int i = 0; i < this.reservasIndex; i++) {
            Reserva reserva = this.reservas[i];
            if (reserva != null) {
                LocalDate reservaDataInicio = reserva.getDataInicio();
                LocalDate reservaDataFinal = reserva.getDataFinal();

                if ((reservaDataInicio.isBefore(datafinal) || reservaDataInicio.isEqual(datafinal)) &&
                        (reservaDataFinal.isAfter(datainicio) || reservaDataFinal.isEqual(datainicio))) {
                    encontradas++;
                }
            }
        }

        if (encontradas == 0) {
            throw new NenhumaReservaException();
        }

        Reserva[] reservasDentroDoPeriodo = new Reserva[encontradas];
        int index = 0;

        for (int i = 0; i < this.reservasIndex; i++) {
            Reserva reserva = this.reservas[i];
            if (reserva != null) {
                LocalDate reservaDataInicio = reserva.getDataInicio();
                LocalDate reservaDataFinal = reserva.getDataFinal();

                if ((reservaDataInicio.isBefore(datafinal) || reservaDataInicio.isEqual(datafinal)) &&
                        (reservaDataFinal.isAfter(datainicio) || reservaDataFinal.isEqual(datainicio))) {
                    reservasDentroDoPeriodo[index] = reserva;
                    index++;
                }
            }
        }

        return reservasDentroDoPeriodo;
    }


    public String toString() {
        String resultado = "\n\nLista de reservas: \n\n";

        for (int i = 0; i < reservasIndex; i++) {
            resultado += reservas[i].toString() + "\n";
        }

        return resultado;
    }

    public String Relatorio(Reserva[] reservasFiltradas) {
        String relatoriozinho = "";


        for (int i = 0; i < reservasFiltradas.length; i++) {

            relatoriozinho += reservasFiltradas[i].gerarRelatorio() + "\n";
        }

        return relatoriozinho;
    }

    public String gerarRelatorioPorCliente(int idCliente) throws NenhumaReservaException {

        Reserva[] reservasDoCliente = buscarReservasPorCliente(idCliente);

        return Relatorio(reservasDoCliente);
    }

    public float Faturamento(Reserva[] reservasEncontradas) {
        float faturamento = 0;

        for (int i = 0; i < reservasEncontradas.length; i++) {
            faturamento += reservasEncontradas[i].valorTotal();
        }
        return faturamento;
    }

    public float gerarFaturamentoPorPeriodo(LocalDate datainicio, LocalDate datafinal) throws NenhumaReservaException, DataInvalidaException {
        if (datafinal.isAfter(datainicio)) {
            Reserva[] reservasNoPeriodo = buscarReservasPorPeriodo(datainicio, datafinal);


            return Faturamento(reservasNoPeriodo);
        } else {
            throw new DataInvalidaException();

        }
    }

}



