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

    @FXML
    private Label LabelTelefone;

    @FXML
    private Label LabelEmail;


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
        this.setCadastro(null);
        SceneManager sceneManager = SceneManager.getInstance();

        sceneManager.changeScreen("TelaLoginAdm.fxml", "Tela Login Administrador");
    }

    public void inicializar(){
        if(cadastro != null) {
            LabelNome.setText(cadastro.getNome());
            LabelCpf.setText(cadastro.getCpf());
            LabelTelefone.setText(cadastro.getTelefone());
            LabelEmail.setText(cadastro.getEmail());
        }
    }
}
