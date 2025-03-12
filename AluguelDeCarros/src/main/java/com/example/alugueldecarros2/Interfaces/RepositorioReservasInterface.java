package com.example.alugueldecarros2.Interfaces;

import com.example.alugueldecarros2.Exceptions.DataInvalidaException;
import com.example.alugueldecarros2.Exceptions.Reservas.NenhumaReservaException;
import com.example.alugueldecarros2.Negocio.Basico.Reserva;

import java.time.LocalDate;

public interface RepositorioReservasInterface {


    //Métodos de modificação de itens da lista.

    void adicionarReserva(Reserva reserva);

    void removerReserva(int idReserva);

    void atualizarReserva(Reserva reservaAtualizada);



    //Métodos de obtenção de informações da lista.

    int getMaiorId();

    float gerarFaturamentoPorPeriodo (LocalDate datainicio, LocalDate datafinal)
            throws NenhumaReservaException, DataInvalidaException;

    float[] getFaturamentoNoPeriodo(LocalDate dataInicio, LocalDate dataFim);


    //Métodos de obtenção de itens da lista.

    Reserva buscarReserva(int numero);

    Reserva[] getListaReservas(String categoriaCarro, String categoriaPreco);

    Reserva[] getListaReservasPorCategoria(String categoria);

    Reserva[] getListaReservasPorPreco(String categoriaPreco);

    Reserva[] buscarReservasPorCliente(int idCliente);

    Reserva[] buscarReservasPorCarro(int idCarro);

    Reserva[] buscarReservasPorPeriodo(LocalDate datainicio, LocalDate datafinal)
            throws NenhumaReservaException;

    Reserva[] getListaInicialReservas();
}

