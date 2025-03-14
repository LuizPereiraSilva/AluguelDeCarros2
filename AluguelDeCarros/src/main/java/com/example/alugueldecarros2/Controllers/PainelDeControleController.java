package com.example.alugueldecarros2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PainelDeControleController {

    @FXML
    private Button AccessCarrosAdmButton;

    @FXML
    private Button AccessPerfilAdmButton;

    @FXML
    private Button AccessReservasAdmButton;

    @FXML
    private Button AccessUsersAdmButton;

    @FXML
    private Button AccessFaturamentoAdmButton;

    @FXML
    void handleAccessCarrosAdmButton(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PesquisarCarroAdm.fxml",
                "Pesquisa de Carros do Administrador");
    }

    @FXML
    void handleAccessPerfilAdmButtonAction(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PerfilAdm.fxml",
                "Perfil do Administrador ");
    }

    @FXML
    void handleAccessReservasAdmButton(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.getPesquisarReservasAdmController().initialize();
        sceneManager.changeScreen("PesquisarReservasAdm.fxml",
                "Pesquisa de Reservas do Administrador");
    }

    @FXML
    void handleAccessUsersAdmButton(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.getPesquisarUsuarioAdmController().inicializar();
        sceneManager.changeScreen("PesquisarUsuarioAdm.fxml",
                "Pesquisa de Usuarios do Administrador");
    }

    @FXML
    void handleAccessFaturamentoAdmButton(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("TelaFaturamento.fxml", "Tela de Faturamento");
        sceneManager.getTelaFaturamentoController().setFieldsNull();
    }


}

