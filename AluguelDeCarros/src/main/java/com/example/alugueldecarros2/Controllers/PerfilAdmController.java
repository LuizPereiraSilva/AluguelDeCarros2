package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PerfilAdmController {

    @FXML
    private Label LabelCpf;

    @FXML
    private Label LabelNome;

    @FXML
    private Button SairButton;

    @FXML
    private Button VoltarButton;

    private Conta cadastro;

    public Conta getCadastro(){
        return cadastro;
    }

    public void setCadastro(Conta cadastro){
        this.cadastro = cadastro;
    }

    @FXML
    void btnVoltarClicked(ActionEvent event){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PainelDeControle.fxml",
                "Painel de Controle");
    }

    @FXML
    void btnSairClicked(ActionEvent event){

    }
}
