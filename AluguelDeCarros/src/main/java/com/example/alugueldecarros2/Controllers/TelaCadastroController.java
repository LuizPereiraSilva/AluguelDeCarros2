package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaCadastroController {

    @FXML
    private Button btnVoltarLogin;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField TextCpf;

    @FXML
    private PasswordField TextSenha;

    @FXML
    private TextField TextTelefone;

    @FXML
    private TextField TextEmail;

    @FXML
    private TextField TextNome;

    @FXML
    private TextField TextSobrenome;

    private Fachada fachada = Fachada.getInstance();


    @FXML
    void handleVoltarLoginButtonAction(){


    }

    @FXML
    void handleConcluirCadastroButtonAction(){

    }








}
