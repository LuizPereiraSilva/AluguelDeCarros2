package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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

    @FXML
    void handleCadastroButtonAction(ActionEvent event) {

        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("TelaCadastro.fxml", "TelaCadastro");

    }

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String cpf = TextCpf.getText();
        String password = TextPassword.getText();
        Fachada fachada = Fachada.getInstance();
        Conta auxConta = null;
        SceneManager sceneManager = SceneManager.getInstance();

        try{
            auxConta = fachada.buscarContaPeloCpf(cpf);

            if(auxConta.getSenha().equals(password)){
                fachada.setCadastro(auxConta);
                sceneManager.changeScreen("TelaPesquisa.fxml", "Tela Pesquisa");
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Problema durante o login");
                alert.setContentText("Senha incorreta ");
                alert.show();
            }
        } catch(ContaNaoExisteException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Problema durante o login");
            alert.setContentText("Conta não existe");
            alert.show();
        }
    }

    public void setFieldsNull(){
        TextCpf.setText(null);
        TextPassword.setText(null);
    }
}
