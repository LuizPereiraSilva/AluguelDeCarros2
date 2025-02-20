package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PerfilClienteController{

    @FXML
    private Label LabelCpf;

    @FXML
    private Label LabelNome;

    @FXML
    private Button SairButton;

    @FXML
    private TableView<?> TabelaReservas;

    @FXML
    private Button VoltarButton;

    @FXML
    void handleSairButtonAction(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        Fachada fachada = Fachada.getInstance();

        sceneManager.changeScreen("LoginTela.fxml", "Login Tela");
        fachada.setCadastro(null);
    }

    @FXML
    void handleVoltarButtonAction(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("TelaPesquisa.fxml", "Tela Pesquisa");
    }

    public void initialize(){
        Fachada fachada = Fachada.getInstance();
        LabelCpf.setText(fachada.getCadastro().getCpf());
        LabelNome.setText(fachada.getCadastro().getNome());
    }

}
