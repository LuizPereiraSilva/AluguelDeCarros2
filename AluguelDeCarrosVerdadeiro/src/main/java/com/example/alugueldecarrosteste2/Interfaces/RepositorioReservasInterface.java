package com.example.alugueldecarrosteste2.Interfaces;

import com.example.alugueldecarrosteste2.Exceptions.DataInvalidaException;
import com.example.alugueldecarrosteste2.Exceptions.Reservas.NenhumaReservaException;
import com.example.alugueldecarrosteste2.Negocio.Basico.Reserva;

import java.time.LocalDate;

public interface RepositorioReservasInterface {

    public void adicionarReserva(Reserva reserva);

    public void removerReserva(int idReserva);

    public Reserva buscarReserva(int numero);

    public void atualizarReserva(Reserva reservaAtualizada);

    public Reserva[] buscarReservasPorCliente(int idCliente) throws NenhumaReservaException;

    public Reserva[] buscarReservasPorCarro(int idCarro) throws NenhumaReservaException;

    public Reserva[] buscarReservasPorPeriodo(LocalDate datainicio, LocalDate datafinal) throws NenhumaReservaException;

    public String gerarRelatorioPorCliente (int idCliente) throws NenhumaReservaException;

    public float gerarFaturamentoPorPeriodo (LocalDate datainicio, LocalDate datafinal) throws NenhumaReservaException, DataInvalidaException;

}

