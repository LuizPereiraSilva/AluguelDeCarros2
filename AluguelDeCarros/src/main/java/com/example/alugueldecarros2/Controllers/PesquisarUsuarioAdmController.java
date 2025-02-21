package com.example.alugueldecarros2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PesquisarUsuarioAdmController {

    @FXML
    private Button VoltarButton;

    @FXML
    private Button NovoAdmButton;

    @FXML
    private TextField TextCpf;

    @FXML
    private ListView<?> ListaUsuarios;

    @FXML
    void handleVoltarButtonAction(ActionEvent event){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PainelDeControle.fxml",
                "Painel de Controle");
    }

    @FXML
    void handleNovoAdmButton(ActionEvent event){

    }


}
