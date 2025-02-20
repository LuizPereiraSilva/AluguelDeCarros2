package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Basico.Carro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private ListView<Carro> ListResultados;


    @FXML
    private Button VoltarButton1;

    public void corregarCarros(){


    }


    @FXML
    void handleVoltarButton1Action(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.getPerfilClienteController().initialize();
        sceneManager.changeScreen("PerfilCliente.fxml", "PerfilCliente ");

    }
}
