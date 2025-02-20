package com.example.alugueldecarros2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

public class TelaPesquisaController {

    @FXML
    private ChoiceBox<?> CategoriaCarroChoiceBox;

    @FXML
    private ChoiceBox<?> CategoriaPrecoChoiceBox;

    @FXML
    private DatePicker DataFinalDatePicker;

    @FXML
    private DatePicker DataInicialDatePicker;

    @FXML
    private ListView<?> ListResultados;


    @FXML
    private Button VoltarButton1;


    @FXML
    void handleVoltarButton1Action(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.getPerfilClienteController().initialize();
        sceneManager.changeScreen("PerfilCliente.fxml", "PerfilCliente ");

    }
}
