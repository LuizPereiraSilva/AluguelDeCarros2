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
    void handleVoltarLoginButtonAction() {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("LoginTela.fxml", "Tela Login");
    }

    @FXML
    void handleConcluirCadastroButtonAction() {
        Fachada fachada = Fachada.getInstance();
        Conta contaAux = null;
        if (!TextName.getText().isEmpty() && !TextSobrenome.getText().isEmpty() &&
                !TextTelefone.getText().isEmpty() && !TextEmail.getText().isEmpty() &&
                !TextCpf.getText().isEmpty() && !TextSenha.getText().isEmpty()) {
            try {

                String nome = TextName.getText() + " " + TextSobrenome.getText();
                fachada.cadastrarCliente(nome, TextCpf.getText(),
                        TextTelefone.getText(), TextEmail.getText(), TextSenha.getText());

                SceneManager sceneManager = SceneManager.getInstance();
                sceneManager.changeScreen("LoginTela.fxml", "Tela Login");

            } catch (ContaJaExisteException | RepositorioCheioException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro ao cadastrar conta ");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao cadastrar conta ");
            alert.setHeaderText(null);
            alert.setContentText("Todos os campos devem estar preenchidos. ");
            alert.show();
        }
    }

    public void setAllFIeldsNull(){
        TextCpf.setText(null);
        TextTelefone.setText(null);
        TextEmail.setText(null);
        TextName.setText(null);
        TextSobrenome.setText(null);
        TextSenha.setText(null);

    }

}
