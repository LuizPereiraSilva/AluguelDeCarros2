package com.example.alugueldecarros2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimeiraTelaContoller {

    @FXML
    private Button LoginAdmButton;

    @FXML
    private Button LoginClienteButton;

    @FXML
    void handleLoginAdmButton(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("TelaLoginAdm.fxml", "TelaLoginAdm");
    }

    @FXML
    void handleLoginClienteButton(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("TelaLoginCliente.fxml", "TelaLoginCliente");
    }

}
