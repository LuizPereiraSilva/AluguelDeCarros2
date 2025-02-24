package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Basico.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TelaReservaConfirmadaController {

    @FXML
    private Label BibliotecaUserLabel;

    @FXML
    private Label DataFinal;

    @FXML
    private Label DataInicial;

    @FXML
    private Label GeneroLivro;

    @FXML
    private Label GeneroLivro1;

    @FXML
    private Label LabelCpf;

    @FXML
    private Label LabelNome;

    @FXML
    private Label ModeloCarro;

    @FXML
    private Label NumeroReserva;

    @FXML
    private Label NumeroReserva1;

    @FXML
    private Button PaginaInicialButton;

    @FXML
    private Label ValorDiaria;

    @FXML
    private Label ValorTotal;


    private Reserva reserva;

    @FXML
    void handlePaginaInicialButton(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("TelaPesquisa.fxml", "Tela de pesquisa");
    }


    void initialize(Reserva reserva){
        this.reserva = reserva;
        DataFinal.setText(reserva.getDataFinal().toString());
        DataInicial.setText(reserva.getDataInicio().toString());
        NumeroReserva.setText("" + reserva.getNumero());
        ModeloCarro.setText(reserva.getCarro().getModelo());
        NumeroReserva1.setText(reserva.getCarro().getPlaca());
        ValorDiaria.setText("R$ " + reserva.getCarro().getPreco());
        ValorTotal.setText("R$ " + reserva.getValorTotal());
        LabelNome.setText(reserva.getCliente().getNome());
        LabelCpf.setText(reserva.getCliente().getCpf());
    }

}


