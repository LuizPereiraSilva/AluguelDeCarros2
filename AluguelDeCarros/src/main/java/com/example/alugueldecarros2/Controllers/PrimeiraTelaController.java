package com.example.alugueldecarros2.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimeiraTelaController {


    @FXML
    private Button btnCliente;

    @FXML
    private Button btnAdm;


    @FXML
    void ClienteBtnClicked(){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("LoginTela.fxml", "Login Cliente");
    }

    @FXML
    void AdmBtnClicked(){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("TelaLoginAdm.fxml", "Login Administrador");
        sceneManager.getTelaLoginAdmController().setFieldsNull();
    }


}
