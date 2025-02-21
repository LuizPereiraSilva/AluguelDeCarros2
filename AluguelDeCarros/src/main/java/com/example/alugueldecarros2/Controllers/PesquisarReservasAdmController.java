package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Basico.Carro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PesquisarReservasAdmController {

    @FXML
    private TextField TextNumeroReserva;

    @FXML
    private TextField TextCpf;

    @FXML
    private ChoiceBox<?> CategoriaCarroChoiceBox;

    @FXML
    private ChoiceBox<?> CategoriaPrecoChoiceBox;

    @FXML
    private DatePicker DataFinalDatePicker;

    @FXML
    private DatePicker DataInicialDatePicker;

    @FXML
    private ListView<Carro> ListResultados;

    @FXML
    private Button VoltarButton;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button GerarRelatorioButton;

    @FXML
    void handleVoltarButtonAction(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PainelDeControle.fxml",
                "Painel de Controle");
    }

    @FXML
    void handleVoltarPerfilAction(ActionEvent event) {

    }

    @FXML
    void handleGerarRelatorioButtonAction(ActionEvent event) {

    }







}
