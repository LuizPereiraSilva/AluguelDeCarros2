package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Controllers.SceneManager.SceneManager;
import com.example.alugueldecarros2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginTelaController{

    @FXML
    private Button CadastroButton;

    @FXML
    private ImageView ImagemLogo;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField TextCpf;

    @FXML
    private PasswordField TextPassword;

    private Fachada fachada = Fachada.getInstance();

    private Conta login = null;

    @FXML
    void handleCadastroButtonAction(ActionEvent event) {

        SceneManager sceneManager = SceneManager.getInstance();

        sceneManager.changeScreen("TelaCadastro.fxml", "TelaCadastro");

    }

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String cpf = TextCpf.getText();
        String password = TextPassword.getText();
        Conta auxConta = null;
        SceneManager sceneManager = SceneManager.getInstance();

        try{
            login = fachada.buscarContaPeloCpf(cpf);
        } catch(ContaNaoExisteException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Conta Não encontrada");
            alert.setContentText("Conta não existe");
            alert.show();
        }

        if(login != null){
            sceneManager.changeScreen("TelaPesquisa.fxml", "TelaPesquisa");
        }
    }
}
