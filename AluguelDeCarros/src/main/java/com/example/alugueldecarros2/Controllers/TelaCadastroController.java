package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.Contas.ContaJaExisteException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private TextField TextName;

    @FXML
    private TextField TextSobrenome;

    private Fachada fachada = Fachada.getInstance();


    @FXML
    void handleVoltarLoginButtonAction(){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("LoginTela.fxml", "Tela Login");
    }

    @FXML
    void handleConcluirCadastroButtonAction(){
        Fachada fachada = Fachada.getInstance();
        Conta contaAux = null;

        try{

            String nome = TextName.getText() + TextSobrenome.getText();
            fachada.cadastrarCliente(nome, TextCpf.getText(),
                    TextTelefone.getText(), TextEmail.getText(), TextSenha.getText());

            SceneManager sceneManager = SceneManager.getInstance();
            sceneManager.changeScreen("LoginTela.fxml", "Tela Login");

        } catch (ContaJaExisteException | RepositorioCheioException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao cadastrar conta ");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }








}
